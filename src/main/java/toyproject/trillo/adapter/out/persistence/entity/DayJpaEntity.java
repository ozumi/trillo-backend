package toyproject.trillo.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "days")
@Getter
@Setter
public class DayJpaEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trips_id")
    private TripJpaEntity trip;

    @Column
    private Date date;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<ScheduleJpaEntity> schedules;

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
