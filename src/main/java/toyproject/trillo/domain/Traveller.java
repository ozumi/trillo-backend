package toyproject.trillo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import toyproject.trillo.adapter.out.persistence.entity.TripJpaEntity;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@ToString(of = {"id", "name", "badge", "travelledDistance"})
public class Traveller {
    private Long id;
    private String name;
    private ArrayList<TripJpaEntity> trips;
    private int travelledDistance;
    private Badge badge;
}
