package toyproject.trillo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Location {
    private String placeId;
    private String placeName;
    private Long latitude;
    private Long longitude;
}
