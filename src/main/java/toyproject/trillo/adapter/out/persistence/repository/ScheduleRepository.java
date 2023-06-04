package toyproject.trillo.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.trillo.adapter.out.persistence.entity.ScheduleJpaEntity;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleJpaEntity, Long> {
    List<ScheduleJpaEntity> findByDayId(Long dayId);
    List<ScheduleJpaEntity> findByTripId(Long tripId);
}
