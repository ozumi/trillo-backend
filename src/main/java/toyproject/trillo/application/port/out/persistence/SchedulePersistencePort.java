package toyproject.trillo.application.port.out.persistence;

import toyproject.trillo.domain.Schedule;

import java.util.List;

public interface SchedulePersistencePort {
    Schedule createSchedule(Schedule schedule);
    Schedule getSchedule(Long id);
    List<Schedule> getSchedulesByDayId(Long dayId);
    List<Schedule> getSchedulesByTripId(Long tripId);

    Schedule updateSchedule(Schedule schedule);
    void deleteSchedule(Long id);
    void moveSchedule(Long id, Long targetDayId, int targetOrder);

}
