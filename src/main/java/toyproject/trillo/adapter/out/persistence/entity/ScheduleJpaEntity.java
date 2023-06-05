package toyproject.trillo.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import toyproject.trillo.domain.Location;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class ScheduleJpaEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trips_id")
    private TripJpaEntity trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "days_id")
    private DayJpaEntity day;

    @Column
    private String title;

    @Column
    @Embedded
    private Location location;

    @Column
    private LocalTime startTime;

    @Column
    private LocalTime endTime;

    @Column
    private int order;

    @Column
    private String content;

    public void changeDay(DayJpaEntity targetDay, int targetOrder) {
        if(this.day != null)
            this.day.removeSchedule(this.order);
        else
            this.trip.removeTempSchedule(this.order);
        this.day = targetDay;
        this.order = targetOrder;
        targetDay.addSchedule(this, targetOrder);
    }

    public void moveToTemp(int targetOrder) {
        if(this.day != null)
            this.day.removeSchedule(this.order);
        else
            this.trip.removeTempSchedule(this.order);
        this.day = null;
        this.order = targetOrder;
        this.trip.addTempSchedule(this, targetOrder);
    }
}
