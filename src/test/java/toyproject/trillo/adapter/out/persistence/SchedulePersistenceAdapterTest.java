package toyproject.trillo.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import toyproject.trillo.adapter.out.persistence.entity.*;
import toyproject.trillo.adapter.out.persistence.repository.DayRepository;
import toyproject.trillo.adapter.out.persistence.repository.ScheduleRepository;
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

@ExtendWith(MockitoExtension.class)
public class SchedulePersistenceAdapterTest {
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private DayRepository dayRepository;
    @Spy
    private SchedulePersistenceMapper schedulePersistenceMapper = Mappers.getMapper(SchedulePersistenceMapper.class);
    @Spy
    private DayPersistenceMapper dayPersistenceMapper = Mappers.getMapper(DayPersistenceMapper.class);
    @InjectMocks
    private SchedulePersistenceAdapter schedulePersistenceAdapter;

    TravellerJpaEntity travellerJpaEntity = TravellerJpaEntity.builder()
            .id(1L)
            .name("tester")
            .badge(Badge.NORMAL)
            .build();

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

    ScheduleJpaEntity scheduleJpaEntity = ScheduleJpaEntity.builder()
            .id(1000L)
            .title("test_schedule")
            .day(dayJpaEntity)
            .trip(tripJpaEntity)
            .order(0)
            .startTime(LocalTime.of(12,0))
            .endTime(LocalTime.of(16,0))
            .build();

    ArrayList<ScheduleJpaEntity> scheduleJpaEntityList = new ArrayList<>(Arrays.asList(scheduleJpaEntity));

    Traveller traveller = Traveller.builder()
            .id(1L)
            .name("tester")
            .badge(Badge.NORMAL)
            .build();

    Trip trip = Trip.builder()
            .id(10L)
            .traveller(traveller)
            .title("test_trip")
            .status(TripStatus.TRAVELLING)
            .startDate(LocalDate.of(2023, 5, 1))
            .endDate(LocalDate.of(2023, 5, 10))
            .build();

    Day day = Day.builder()
            .id(100L)
            .trip(trip)
            .date(LocalDate.of(2023, 5, 1))
            .color("#000000")
            .build();
    Schedule schedule = Schedule.builder()
            .id(1000L)
            .day(day)
            .trip(trip)
            .order(0)
            .startTime(LocalTime.of(12,0))
            .endTime(LocalTime.of(16,0))
            .title("test_schedule")
            .build();

    ArrayList<Schedule> scheduleList = new ArrayList<>(Arrays.asList(schedule));

    @Test
    void success_createSchedule() {
        //given
        given(scheduleRepository.save(any())).willReturn(scheduleJpaEntity);

        //when
        Schedule actual = schedulePersistenceAdapter.createSchedule(schedule);

        //then
        assertThat(schedule.getId()).isEqualTo(actual.getId());
        assertThat(schedule.getTitle()).isEqualTo(actual.getTitle());
    }

    @Test
    void success_getSchedule() {
        //given
        given(scheduleRepository.findById(any())).willReturn(Optional.ofNullable(scheduleJpaEntity));

        //when
        Schedule actual = schedulePersistenceAdapter.getSchedule(schedule.getId());

        //then
        assertThat(schedule.getId()).isEqualTo(actual.getId());
        assertThat(schedule.getTitle()).isEqualTo(actual.getTitle());
    }

    @Test
    void success_getSchedulesByDayId() {
        //given
        given(scheduleRepository.findByDayId(any())).willReturn(scheduleJpaEntityList);

        //when
        List<Schedule> actual = schedulePersistenceAdapter.getSchedulesByDayId(day.getId());

        //then
        assertThat(scheduleList.size()).isEqualTo(actual.size());
        assertThat(scheduleList.get(0).getDay().getId()).isEqualTo(actual.get(0).getDay().getId());
    }

    @Test
    void success_getSchedulesByTripId(){
        //given
        given(scheduleRepository.findByTripId(any())).willReturn(scheduleJpaEntityList);

        //when
        List<Schedule> actual = schedulePersistenceAdapter.getSchedulesByTripId(trip.getId());

        //then
        assertThat(scheduleList.size()).isEqualTo(actual.size());
        assertThat(scheduleList.get(0).getTrip().getId()).isEqualTo(actual.get(0).getTrip().getId());
    }

    @Test
    void success_updateSchedule() {
        //given
        given(scheduleRepository.existsById(any())).willReturn(true);
        given(scheduleRepository.save(any())).willReturn(scheduleJpaEntity);

        //when
        Schedule actual = schedulePersistenceAdapter.updateSchedule(schedule);

        //then
        assertThat(schedule.getId()).isEqualTo(actual.getId());
        assertThat(schedule.getTrip().getId()).isEqualTo(actual.getTrip().getId());
        assertThat(schedule.getDay().getId()).isEqualTo(actual.getDay().getId());
    }

    @Test
    void success_deleteSchedule() {
        //given
        given(scheduleRepository.existsById(any())).willReturn(true);

        //when
        schedulePersistenceAdapter.deleteSchedule(schedule.getId());

        //then
        verify(scheduleRepository, times(1)).deleteById(schedule.getId());
    }

    @Test
    void success_moveScheduleToOtherDay(){
//        //given
//        scheduleJpaEntity.getDay().setSchedules(scheduleJpaEntityList);
//        given(scheduleRepository.findById(any())).willReturn(Optional.ofNullable(scheduleJpaEntity));
//        int targetOrder = 0;
//        DayJpaEntity targetDayJpaEntity = DayJpaEntity.builder()
//                .id(200L)
//                .date(LocalDate.of(2023, 6, 1))
//                .color("#111111")
//                .build();
//        given(dayRepository.findById(any())).willReturn(Optional.ofNullable(targetDayJpaEntity));
//
//        //when
//        Schedule actual = schedulePersistenceAdapter.moveSchedule(schedule.getId(), targetDayJpaEntity.getId(), targetOrder);
//
//        //then
//        assertThat(targetOrder).isEqualTo(actual.getOrder());
//        assertThat(targetDayJpaEntity.getId()).isEqualTo(actual.getDay().getId());
    }

    @Test
    void success_moveScheduleToTemp(){
//        //given
//        given(scheduleRepository.findById(any())).willReturn(Optional.ofNullable(scheduleJpaEntity));
//        given(dayRepository.findById(any())).willReturn(Optional.ofNullable(dayJpaEntity));
//
//        //when
//        int targetOrder = 1;
//        Day targetDay = Day.builder()
//                .id(200L)
//                .date(LocalDate.of(2023, 6, 1))
//                .color("#111111")
//                .build();
//
//        Schedule actual = schedulePersistenceAdapter.moveSchedule(schedule.getId(), day.getId(), targetOrder);
//
//        //then
//        assertThat(targetOrder).isEqualTo(actual.getOrder());
//        assertThat(targetDay.getId()).isEqualTo(actual.getDay().getId());
    }

}
