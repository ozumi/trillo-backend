package toyproject.trillo.adapter.out.persistence.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import toyproject.trillo.domain.TripStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "trips")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(of = {"id", "title", "status", "startDate", "endDate", "image", "travelledDistance"})
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

    @Builder.Default
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<DayJpaEntity> days = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<ScheduleJpaEntity> tempSchedules = new ArrayList<>();

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
