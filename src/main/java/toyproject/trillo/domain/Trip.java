package toyproject.trillo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@Builder
@ToString(of = {"id", "title", "status", "startDate", "endDate", "image", "travelledDistance"})
public class Trip {
    private Long id;
    private Traveller traveller;
    private String title;
    private TripStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Day> days;
    private ArrayList<Schedule> tempSchedules;
    private String image;
    private int travelledDistance;

}
