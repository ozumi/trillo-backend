package toyproject.trillo.adapter.out.persistence.entity;

import org.mapstruct.*;
import toyproject.trillo.domain.Trip;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
        , imports = UUID.class
        , nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TripPersistenceMapper {
    TripJpaEntity tripToTripJpaEntity(Trip trip);
    Trip tripJpaEntityToTrip(TripJpaEntity tripJPAEntity);
    List<Trip> tripJpaEntityListToTripList(List<TripJpaEntity> entityList);
}
