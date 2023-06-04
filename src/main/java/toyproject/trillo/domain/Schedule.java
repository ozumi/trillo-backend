package toyproject.trillo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Schedule {
    private Long id;
    private Trip trip;
    private Day day;
    private String title;
    private Location location;
    private Date startTime;
    private Date endTime;
    private int order;
    private String content;
}
