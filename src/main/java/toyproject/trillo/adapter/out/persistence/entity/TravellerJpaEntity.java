package toyproject.trillo.adapter.out.persistence.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import toyproject.trillo.domain.Badge;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "travellers")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(of = {"id", "name", "badge", "travelledDistance"})
public class TravellerJpaEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "traveller", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<TripJpaEntity> trips = new ArrayList<>();

    @Column
    private int travelledDistance;

    @Column
    @Enumerated(EnumType.STRING)
    private Badge badge;

}
