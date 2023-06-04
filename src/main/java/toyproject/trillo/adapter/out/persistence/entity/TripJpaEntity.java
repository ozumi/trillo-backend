package toyproject.trillo.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import toyproject.trillo.domain.TripStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "trips")
@Getter
@Setter
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
    private TripStatus status;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<DayJpaEntity> days;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<ScheduleJpaEntity> tempSchedules;

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
