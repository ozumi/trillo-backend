package toyproject.trillo.adapter.out.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import toyproject.trillo.adapter.out.persistence.entity.*;
import toyproject.trillo.adapter.out.persistence.repository.TripRepository;
import toyproject.trillo.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TripPersistenceAdapterTest {
    @Mock
    private TripRepository tripRepository;
    @Spy
    private TripPersistenceMapper tripPersistenceMapper = Mappers.getMapper(TripPersistenceMapper.class);
    @Spy
    private SchedulePersistenceMapper schedulePersistenceMapper = Mappers.getMapper(SchedulePersistenceMapper.class);

    @InjectMocks
    private TripPersistenceAdapter tripPersistenceAdapter;


    TravellerJpaEntity travellerJpaEntity = TravellerJpaEntity.builder()
            .id(1L)
            .name("tester")
            .badge(Badge.NORMAL)
            .build();

    ScheduleJpaEntity tempScheduleJpaEntity = ScheduleJpaEntity.builder()
            .id(1000L)
            .title("temp_schedule")
            .startTime(LocalTime.of(12,0))
            .endTime(LocalTime.of(16,0))
            .build();

    ArrayList<ScheduleJpaEntity> tempScheduleJpaEntityList = new ArrayList<>(Arrays.asList(tempScheduleJpaEntity));

    TripJpaEntity tripJpaEntity = TripJpaEntity.builder()
            .id(10L)
            .traveller(travellerJpaEntity)
            .title("test_trip")
            .status(TripStatus.TRAVELLING)
            .startDate(LocalDate.of(2023, 5, 1))
            .endDate(LocalDate.of(2023, 5, 10))
            .build();

    DayJpaEntity dayJpaEntity = DayJpaEntity.builder()
            .id(100L)
            .trip(tripJpaEntity)
            .date(LocalDate.of(2023, 5, 1))
            .color("#000000")
            .build();

    TripJpaEntity tripTempJpaEntity = TripJpaEntity.builder()
            .id(20L)
            .traveller(travellerJpaEntity)
            .title("test_trip2")
            .status(TripStatus.NOT_STARTED)
            .days(new ArrayList<>(Arrays.asList(dayJpaEntity)))
            .startDate(LocalDate.of(2023, 7, 1))
            .endDate(LocalDate.of(2023, 7, 10))
            .tempSchedules(tempScheduleJpaEntityList)
            .build();

    List<TripJpaEntity> tripJpaEntityList = new ArrayList<>(Arrays.asList(tripJpaEntity, tripTempJpaEntity));

    Traveller traveller = Traveller.builder()
            .id(1L)
            .name("tester")
            .badge(Badge.NORMAL)
            .build();

    Schedule tempSchedule = Schedule.builder()
            .id(1000L)
            .title("temp_schedule")
            .build();

    ArrayList<Schedule> tempScheduleList = new ArrayList<>(Arrays.asList(tempSchedule));

    Trip trip = Trip.builder()
            .id(10L)
            .traveller(traveller)
            .title("test_trip")
            .status(TripStatus.TRAVELLING)
            .startDate(LocalDate.of(2023, 5, 1))
            .endDate(LocalDate.of(2023, 5, 10))
            .tempSchedules(tempScheduleList)
            .build();

    Day day = Day.builder()
            .id(100L)
            .trip(trip)
            .date(LocalDate.of(2023, 5, 1))
            .color("#000000")
            .build();

    Trip tripTemp = Trip.builder()
            .id(20L)
            .traveller(traveller)
            .title("test_trip2")
            .status(TripStatus.NOT_STARTED)
            .days(new ArrayList<>(Arrays.asList(day)))
            .startDate(LocalDate.of(2023, 7, 1))
            .endDate(LocalDate.of(2023, 7, 10))
            .build();

    List<Trip> tripList = new ArrayList<>(Arrays.asList(trip, tripTemp));

    @Test
    void success_createTrip() {
        //given
        given(tripRepository.save(any())).willReturn(tripJpaEntity);

        //when
        Trip actual = tripPersistenceAdapter.createTrip(trip);

        //then
        assertThat(trip.getId()).isEqualTo(actual.getId());
        assertThat(trip.getTraveller().getId()).isEqualTo(actual.getTraveller().getId());
        assertThat(trip.getStartDate()).isEqualTo(actual.getStartDate());
    }

    @Test
    void success_getTrip() {
        //given
        given(tripRepository.findById(any())).willReturn(Optional.ofNullable(tripJpaEntity));

        //when
        Trip actual = tripPersistenceAdapter.getTrip(trip.getId());

        //then
        assertThat(trip.getId()).isEqualTo(actual.getId());
        assertThat(trip.getTraveller().getId()).isEqualTo(actual.getTraveller().getId());
        assertThat(trip.getStartDate()).isEqualTo(actual.getStartDate());
    }

    @Test
    void success_getTrips() {
        //given
        given(tripRepository.findByTravellerId(any())).willReturn(tripJpaEntityList);

        //when
        List<Trip> actual = tripPersistenceAdapter.getTrips(trip.getId());

        //then
        assertThat(tripList.size()).isEqualTo(actual.size());
        assertThat(tripList.get(0).getId()).isEqualTo(actual.get(0).getId());
    }

    @Test
    void success_getTempSchedules() {
        //given
        given(tripRepository.findById(any())).willReturn(Optional.ofNullable(tripTempJpaEntity));
        //log.info("temo  {}", tripTempJpaEntity.getTempSchedules());

        //when
        List<Schedule> actual = tripPersistenceAdapter.getTempSchedules(tripTemp.getId());
        //then
        assertThat(tempScheduleList.size()).isEqualTo(actual.size());
        assertThat(tempScheduleList.get(0).getId()).isEqualTo(actual.get(0).getId());
    }

    @Test
    void success_updateTrip() {
        //given
        given(tripRepository.existsById(any())).willReturn(true);
        given(tripRepository.save(any())).willReturn(tripJpaEntity);

        //when
        Trip actual = tripPersistenceAdapter.updateTrip(trip);

        //then
        assertThat(trip.getId()).isEqualTo(actual.getId());
        assertThat(trip.getTraveller().getId()).isEqualTo(actual.getTraveller().getId());
        assertThat(trip.getStatus()).isEqualTo(actual.getStatus());
    }

    @Test
    void success_deleteTrip() {
        //given
        given(tripRepository.existsById(any())).willReturn(true);

        //when
        tripPersistenceAdapter.deleteTrip(trip.getId());

        //then
        verify(tripRepository, times(1)).deleteById(trip.getId());
    }

}