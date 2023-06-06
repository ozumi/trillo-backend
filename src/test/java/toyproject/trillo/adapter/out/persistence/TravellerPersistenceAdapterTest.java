package toyproject.trillo.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import toyproject.trillo.adapter.out.persistence.entity.TravellerJpaEntity;
import toyproject.trillo.adapter.out.persistence.entity.TravellerPersistenceMapper;
import toyproject.trillo.adapter.out.persistence.repository.TravellerRepository;
import toyproject.trillo.domain.Badge;
import toyproject.trillo.domain.Traveller;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TravellerPersistenceAdapterTest {
    @Mock
    private TravellerRepository travellerRepository;
    @Spy
    private TravellerPersistenceMapper travellerPersistenceMapper = Mappers.getMapper(TravellerPersistenceMapper.class);
    @InjectMocks
    private TravellerPersistenceAdapter travellerPersistenceAdapter;

    TravellerJpaEntity travellerJpaEntity = TravellerJpaEntity.builder()
            .id(1L)
            .name("tester")
            .badge(Badge.NORMAL)
            .build();

    Traveller traveller = Traveller.builder()
            .id(1L)
            .name("tester")
            .badge(Badge.NORMAL)
            .build();

    @Test
    void success_createTraveller() {
        //given
        given(travellerRepository.save(any())).willReturn(travellerJpaEntity);

        //when
        Traveller actual = travellerPersistenceAdapter.createTraveller(traveller);

        //then
        assertThat(traveller.getId()).isEqualTo(actual.getId());
    }

    @Test
    void success_getTraveller() {
        //given
        given(travellerRepository.findById(any())).willReturn(Optional.ofNullable(travellerJpaEntity));

        //when
        Traveller actual = travellerPersistenceAdapter.getTraveller(traveller.getId());

        //then
        assertThat(traveller.getId()).isEqualTo(actual.getId());
    }

    @Test
    void success_updateTraveller() {
        //given
        given(travellerRepository.existsById(any())).willReturn(true);
        given(travellerRepository.save(any())).willReturn(travellerJpaEntity);

        //when
        Traveller actual = travellerPersistenceAdapter.updateTraveller(traveller);

        //then
        assertThat(traveller.getId()).isEqualTo(actual.getId());
    }

    @Test
    void success_deleteTraveller() {
        //given
        given(travellerRepository.existsById(any())).willReturn(true);

        //when
        travellerPersistenceAdapter.deleteTraveller(traveller.getId());

        //then
        verify(travellerRepository, times(1)).deleteById(traveller.getId());

    }
}