package toyproject.trillo.application.port.out.persistence;

import toyproject.trillo.domain.Day;

import java.util.List;

public interface DayPersistencePort {
    Day createDay(Day day);
    Day updateDay(Day day);
    List<Day> getDays(Long tripId);
    void deleteDay(Long id);
}
