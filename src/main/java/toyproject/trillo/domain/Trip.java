package toyproject.trillo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class Trip {
    private Long id;
    private Traveller traveller;
    private String title;
    private TripStatus status;
    private Date startDate;
    private Date endDate;
    private ArrayList<Day> days;
    private ArrayList<Schedule> tempSchedules;
    private String image;
    private int travelledDistance;

}
