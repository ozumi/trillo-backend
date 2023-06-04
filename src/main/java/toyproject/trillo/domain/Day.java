package toyproject.trillo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class Day {
    private Long id;
    private Trip trip;
    private Date date;
    private ArrayList<Schedule> schedules;
    private String color;
    private int travelledDistance;
}
