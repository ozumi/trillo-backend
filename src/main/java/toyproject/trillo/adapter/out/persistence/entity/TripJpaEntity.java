package toyproject.trillo.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import toyproject.trillo.domain.TripStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TripJpaEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travellers_id")
    private TravellerJpaEntity traveller;

    @Column
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private TripStatus status;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DayJpaEntity> days;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleJpaEntity> tempSchedules;

    @Column
    private String image;

    @Column
    private int travelledDistance;

    public void addTempSchedule(ScheduleJpaEntity scheduleJpaEntity, int targetOrder) {
        tempSchedules.add(targetOrder, scheduleJpaEntity);
    }

    public void removeTempSchedule(int targetOrder) {
        tempSchedules.remove(targetOrder);
    }
}
