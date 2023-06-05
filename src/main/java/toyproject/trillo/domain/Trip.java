package toyproject.trillo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Trip {
    private Long id;
    private Traveller traveller;
    private String title;
    private TripStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Day> days;
    private List<Schedule> tempSchedules;
    private String image;
    private int travelledDistance;

}
