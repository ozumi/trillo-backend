package toyproject.trillo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String placeId;
    private String placeName;
    private Long latitude;
    private Long longitude;
}
