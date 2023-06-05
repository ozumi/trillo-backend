package toyproject.trillo.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import toyproject.trillo.domain.Badge;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "travellers")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class TravellerJpaEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "traveller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripJpaEntity> trips;

    @Column
    private int travelledDistance;

    @Column
    @Enumerated(EnumType.STRING)
    private Badge badge;

}
