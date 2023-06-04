package toyproject.trillo.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.trillo.adapter.out.persistence.entity.TripJpaEntity;

import java.util.List;

public interface TripRepository extends JpaRepository<TripJpaEntity, Long> {
    List<TripJpaEntity> findByTravellerId(Long travellerId);
}
