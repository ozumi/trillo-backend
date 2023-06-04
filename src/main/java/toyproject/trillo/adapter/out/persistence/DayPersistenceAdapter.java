package toyproject.trillo.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toyproject.trillo.adapter.out.persistence.entity.DayJpaEntity;
import toyproject.trillo.adapter.out.persistence.entity.DayPersistenceMapper;
import toyproject.trillo.adapter.out.persistence.repository.DayRepository;
import toyproject.trillo.application.port.out.persistence.DayPersistencePort;
import toyproject.trillo.domain.Day;
import toyproject.trillo.exception.ResourceNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DayPersistenceAdapter implements DayPersistencePort {
    private final DayRepository dayRepository;
    private final DayPersistenceMapper dayPersistenceMapper;

    @Override
    public Day createDay(Day day) {
        DayJpaEntity dayJpaEntity = dayPersistenceMapper.dayToDayJpaEntity(day);
        return dayPersistenceMapper.dayJpaEntityToDay(dayRepository.save(dayJpaEntity));
    }

    @Override
    public Day updateDay(Day day) {
        if(!dayRepository.existsById(day.getId()))
            throw new ResourceNotFoundException(ResourceNotFoundException.INVALID_DAY_ID);

        DayJpaEntity dayJpaEntity = dayPersistenceMapper.dayToDayJpaEntity(day);
        return dayPersistenceMapper.dayJpaEntityToDay(dayRepository.save(dayJpaEntity));
    }

    @Override
    public List<Day> getDays(Long tripId) {
        return dayPersistenceMapper.dayJpaEntityListToDayList(dayRepository.findByTripId(tripId));
    }

    @Override
    public void deleteDay(Long id) {
        if(!dayRepository.existsById(id))
            throw new ResourceNotFoundException(ResourceNotFoundException.INVALID_DAY_ID);

        dayRepository.deleteById(id);
    }
}
