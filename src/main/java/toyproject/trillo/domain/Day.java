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
public class Day {
    private Long id;
    private Trip trip;
    private LocalDate date;
    private List<Schedule> schedules;
    private String color;
    private int travelledDistance;
}
