package toyproject.trillo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import toyproject.trillo.adapter.out.persistence.entity.TripJpaEntity;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Traveller {
    private Long id;
    private String name;
    private List<TripJpaEntity> trips;
    private int travelledDistance;
    private Badge badge;
}
