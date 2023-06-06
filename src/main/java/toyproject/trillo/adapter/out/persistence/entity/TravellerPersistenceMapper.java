package toyproject.trillo.adapter.out.persistence.entity;

import org.mapstruct.*;
import toyproject.trillo.domain.Traveller;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
        , imports = UUID.class
        , nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TravellerPersistenceMapper {
    TravellerJpaEntity travellerToTravellerJpaEntity(Traveller traveller);
    Traveller travellerJpaEntityToTraveller(TravellerJpaEntity travellerJpaEntity);
    List<Traveller> travellerJpaEntityListToTravellerList(List<TravellerJpaEntity> entityList);
}
