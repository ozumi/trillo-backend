package toyproject.trillo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@ToString
public class Schedule {
    private Long id;
    private Trip trip;
    private Day day;
    private String title;
    private Location location;
    private LocalTime startTime;
    private LocalTime endTime;
    private int order;
    private String content;
}
