package toyproject.trillo.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toyproject.trillo.adapter.out.persistence.entity.DayJpaEntity;
import toyproject.trillo.adapter.out.persistence.entity.ScheduleJpaEntity;
import toyproject.trillo.adapter.out.persistence.entity.SchedulePersistenceMapper;
import toyproject.trillo.adapter.out.persistence.repository.DayRepository;
import toyproject.trillo.adapter.out.persistence.repository.ScheduleRepository;
import toyproject.trillo.application.port.out.persistence.SchedulePersistencePort;
import toyproject.trillo.domain.Schedule;
import toyproject.trillo.exception.ResourceNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SchedulePersistenceAdapter implements SchedulePersistencePort {
    private final ScheduleRepository scheduleRepository;
    private final DayRepository dayRepository;
    private final SchedulePersistenceMapper schedulePersistenceMapper;

    @Override
    public Schedule createSchedule(Schedule schedule) {
        ScheduleJpaEntity scheduleJpaEntity = schedulePersistenceMapper.scheduleToScheduleJpaEntity(schedule);
        return schedulePersistenceMapper.scheduleJpaEntityToSchedule(scheduleRepository.save(scheduleJpaEntity));
    }

    @Override
    public Schedule getSchedule(Long id) {
        return schedulePersistenceMapper.scheduleJpaEntityToSchedule(
                scheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.INVALID_SCHEDULE_ID)));
    }

    @Override
    public List<Schedule> getSchedulesByDayId(Long dayId) {
        return schedulePersistenceMapper.scheduleJpaEntityListToScheduleList(
                scheduleRepository.findByDayId(dayId)
        );
    }

    @Override
    public List<Schedule> getSchedulesByTripId(Long tripId) {
        return schedulePersistenceMapper.scheduleJpaEntityListToScheduleList(
                scheduleRepository.findByTripId(tripId)
        );
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) {
        if(!scheduleRepository.existsById(schedule.getId()))
            throw new ResourceNotFoundException(ResourceNotFoundException.INVALID_SCHEDULE_ID);

        ScheduleJpaEntity scheduleJpaEntity = schedulePersistenceMapper.scheduleToScheduleJpaEntity(schedule);
        return schedulePersistenceMapper.scheduleJpaEntityToSchedule(scheduleRepository.save(scheduleJpaEntity));
    }

    @Override
    public void deleteSchedule(Long id) {
        if(!scheduleRepository.existsById(id))
            throw new ResourceNotFoundException(ResourceNotFoundException.INVALID_SCHEDULE_ID);

        scheduleRepository.deleteById(id);
    }

    @Override
    public Schedule moveSchedule(Long id, Long targetDayId, int targetOrder) {
        ScheduleJpaEntity scheduleJpaEntity = scheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.INVALID_SCHEDULE_ID));
        if(targetDayId != null) {
            //다른 Day로 옮긴다
            DayJpaEntity dayJpaEntity = dayRepository.findById(targetDayId).orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.INVALID_DAY_ID));
            scheduleJpaEntity.changeDay(dayJpaEntity, targetOrder);
        } else {
            //Temp schedules로 옮긴다
            scheduleJpaEntity.moveToTemp(targetOrder);
        }
        return schedulePersistenceMapper.scheduleJpaEntityToSchedule(scheduleJpaEntity);
    }
}
