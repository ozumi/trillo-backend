package toyproject.trillo.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import toyproject.trillo.adapter.out.persistence.entity.SchedulePersistenceMapper;
import toyproject.trillo.adapter.out.persistence.entity.TripJpaEntity;
import toyproject.trillo.adapter.out.persistence.entity.TripPersistenceMapper;
import toyproject.trillo.adapter.out.persistence.repository.TripRepository;
import toyproject.trillo.application.port.out.persistence.TripPersistencePort;
import toyproject.trillo.domain.Schedule;
import toyproject.trillo.domain.Trip;
import toyproject.trillo.exception.ResourceNotFoundException;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TripPersistenceAdapter implements TripPersistencePort {

    private final TripRepository tripRepository;
    private final TripPersistenceMapper tripPersistenceMapper;
    private final SchedulePersistenceMapper schedulePersistenceMapper;

    @Override
    public Trip createTrip(Trip trip) {
        TripJpaEntity tripJpaEntity = tripPersistenceMapper.tripToTripJpaEntity(trip);
        return tripPersistenceMapper.tripJpaEntityToTrip(tripRepository.save(tripJpaEntity));
    }

    @Override
    public Trip getTrip(Long id) {
        return tripPersistenceMapper.tripJpaEntityToTrip(
                tripRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.INVALID_TRIP_ID)));
    }

    @Override
    public List<Trip> getTrips(Long travellerId) {
        return tripPersistenceMapper.tripJpaEntityListToTripList(tripRepository.findByTravellerId(travellerId));
    }

    @Override
    public List<Schedule> getTempSchedules(Long tripId) {
        TripJpaEntity tripJpaEntity = tripRepository.findById(tripId).orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.INVALID_TRIP_ID));
        return schedulePersistenceMapper.scheduleJpaEntityListToScheduleList(tripJpaEntity.getTempSchedules());
    }

    @Override
    public Trip updateTrip(Trip trip) {
        if(!tripRepository.existsById(trip.getId()))
            throw new ResourceNotFoundException(ResourceNotFoundException.INVALID_TRIP_ID);

        TripJpaEntity tripJpaEntity = tripPersistenceMapper.tripToTripJpaEntity(trip);
        return tripPersistenceMapper.tripJpaEntityToTrip(tripRepository.save(tripJpaEntity));
    }

    @Override
    public void deleteTrip(Long id) {
        if(!tripRepository.existsById(id))
            throw new ResourceNotFoundException(ResourceNotFoundException.INVALID_TRIP_ID);

        tripRepository.deleteById(id);
    }

}
