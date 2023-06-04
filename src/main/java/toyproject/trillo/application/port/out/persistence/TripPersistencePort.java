package toyproject.trillo.application.port.out.persistence;

import toyproject.trillo.domain.Schedule;
import toyproject.trillo.domain.Trip;

import java.util.List;

public interface TripPersistencePort {
    Trip createTrip(Trip trip);
    Trip getTrip(Long id);
    List<Trip> getTrips(Long travellerId);
    List<Schedule> getTempSchedules(Long tripId);
    Trip updateTrip(Trip trip);
    void deleteTrip(Long id);
}
