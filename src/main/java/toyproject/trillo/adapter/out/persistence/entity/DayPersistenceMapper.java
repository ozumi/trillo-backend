package toyproject.trillo.adapter.out.persistence.entity;

import org.mapstruct.*;
import toyproject.trillo.domain.Day;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
        , imports = UUID.class
        , nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DayPersistenceMapper {
    DayJpaEntity dayToDayJpaEntity(Day day);
    Day dayJpaEntityToDay(DayJpaEntity dayJpaEntity);
    List<Day> dayJpaEntityListToDayList(List<DayJpaEntity> entityList);
}
