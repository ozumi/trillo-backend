package toyproject.trillo.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.trillo.adapter.out.persistence.entity.TravellerJpaEntity;

public interface TravellerRepository extends JpaRepository<TravellerJpaEntity, Long> {
}
