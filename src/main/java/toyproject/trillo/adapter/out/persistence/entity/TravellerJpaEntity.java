package toyproject.trillo.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import toyproject.trillo.domain.Badge;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "travellers")
@Getter
@Setter
public class TravellerJpaEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "traveller", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<TripJpaEntity> trips;

    @Column
    private int travelledDistance;

    @Column
    private Badge badge;

}
