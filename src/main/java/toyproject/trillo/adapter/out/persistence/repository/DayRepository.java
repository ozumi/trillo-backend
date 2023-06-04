package toyproject.trillo.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.trillo.adapter.out.persistence.entity.DayJpaEntity;

import java.util.List;

public interface DayRepository extends JpaRepository<DayJpaEntity, Long> {
    List<DayJpaEntity> findByTripId(Long tripId);
}
