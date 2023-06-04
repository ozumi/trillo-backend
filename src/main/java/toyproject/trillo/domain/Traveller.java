package toyproject.trillo.domain;

import lombok.Getter;
import lombok.Setter;
import toyproject.trillo.adapter.out.persistence.entity.TripJpaEntity;

import java.util.ArrayList;

@Getter
@Setter
public class Traveller {
    private Long id;
    private String name;
    private ArrayList<TripJpaEntity> trips;
    private int travelledDistance;
    private Badge badge;
}
