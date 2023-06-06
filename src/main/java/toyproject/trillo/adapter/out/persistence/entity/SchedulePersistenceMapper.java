package toyproject.trillo.adapter.out.persistence.entity;

import org.mapstruct.*;
import toyproject.trillo.domain.Schedule;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
        , imports = UUID.class
        , nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SchedulePersistenceMapper {
    ScheduleJpaEntity scheduleToScheduleJpaEntity(Schedule schedule);
    Schedule scheduleJpaEntityToSchedule(ScheduleJpaEntity scheduleJpaEntity);
    List<Schedule> scheduleJpaEntityListToScheduleList(List<ScheduleJpaEntity> entityList);
}
