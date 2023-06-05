package toyproject.trillo.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "days")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class DayJpaEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trips_id")
    private TripJpaEntity trip;

    @Column
    private LocalDate date;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleJpaEntity> schedules;

    @Column
    private String color;

    @Column
    private int travelledDistance;


    public void addSchedule(ScheduleJpaEntity scheduleJpaEntity, int targetOrder) {
        this.schedules.add(targetOrder, scheduleJpaEntity);
    }

    public void removeSchedule(int order) {
        this.schedules.remove(order);
    }
}
