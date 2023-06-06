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
@ToString(of = {"id", "date", "color", "travelledDistance"})
public class Day {
    private Long id;
    private Trip trip;
    private LocalDate date;
    private ArrayList<Schedule> schedules;
    private String color;
    private int travelledDistance;
}
