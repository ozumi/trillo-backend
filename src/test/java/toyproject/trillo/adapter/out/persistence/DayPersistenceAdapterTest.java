package toyproject.trillo.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import toyproject.trillo.adapter.out.persistence.entity.DayJpaEntity;
import toyproject.trillo.adapter.out.persistence.entity.DayPersistenceMapper;
import toyproject.trillo.adapter.out.persistence.entity.TravellerJpaEntity;
import toyproject.trillo.adapter.out.persistence.entity.TripJpaEntity;
import toyproject.trillo.adapter.out.persistence.repository.DayRepository;
import toyproject.trillo.domain.*;

import java.time.LocalDate;
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
class DayPersistenceAdapterTest {
    @Mock
    private DayRepository dayRepository;
    @Spy
    private DayPersistenceMapper dayPersistenceMapper = Mappers.getMapper(DayPersistenceMapper.class);
    @InjectMocks
    private DayPersistenceAdapter dayPersistenceAdapter;

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

    ArrayList<DayJpaEntity> dayJpaEntityList = new ArrayList<>(Arrays.asList(dayJpaEntity));

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

    ArrayList<Day> dayList = new ArrayList<>(Arrays.asList(day));

    @Test
    void success_createDay() {
        //given
        given(dayRepository.save(any())).willReturn(dayJpaEntity);

        //when
        Day actual = dayPersistenceAdapter.createDay(day);

        //then
        assertThat(day.getId()).isEqualTo(actual.getId());
        assertThat(day.getTrip().getId()).isEqualTo(actual.getTrip().getId());
    }

    @Test
    void success_getDay() {
        //given
        given(dayRepository.findById(any())).willReturn(Optional.ofNullable(dayJpaEntity));

        //when
        Day actual = dayPersistenceAdapter.getDay(day.getId());

        //then
        assertThat(day.getId()).isEqualTo(actual.getId());
        assertThat(day.getTrip().getId()).isEqualTo(actual.getTrip().getId());
    }

    @Test
    void success_updateDay() {
        //given
        given(dayRepository.existsById(any())).willReturn(true);
        given(dayRepository.save(any())).willReturn(dayJpaEntity);

        //when
        Day actual = dayPersistenceAdapter.updateDay(day);

        //then
        assertThat(day.getId()).isEqualTo(actual.getId());
        assertThat(day.getTrip().getId()).isEqualTo(actual.getTrip().getId());
    }

    @Test
    void success_getDays() {
        //given
        given(dayRepository.findByTripId(any())).willReturn(dayJpaEntityList);

        //when
        List<Day> actual = dayPersistenceAdapter.getDays(trip.getId());

        //then
        assertThat(dayList.size()).isEqualTo(actual.size());
        assertThat(dayList.get(0).getId()).isEqualTo(actual.get(0).getId());
    }

    @Test
    void success_deleteDay() {
        //given
        given(dayRepository.existsById(any())).willReturn(true);

        //when
        dayPersistenceAdapter.deleteDay(day.getId());

        //then
        verify(dayRepository, times(1)).deleteById(day.getId());
    }
}