package toyproject.trillo.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toyproject.trillo.adapter.out.persistence.entity.TravellerJpaEntity;
import toyproject.trillo.adapter.out.persistence.entity.TravellerPersistenceMapper;
import toyproject.trillo.adapter.out.persistence.repository.TravellerRepository;
import toyproject.trillo.application.port.out.persistence.TravellerPersistencePort;
import toyproject.trillo.domain.Traveller;
import toyproject.trillo.exception.ResourceNotFoundException;

@Component
@RequiredArgsConstructor
public class TravellerPersistenceAdapter implements TravellerPersistencePort {
    private final TravellerRepository travellerRepository;
    private final TravellerPersistenceMapper travellerPersistenceMapper;

    @Override
    public Traveller createTraveller(Traveller traveller) {
        TravellerJpaEntity travellerJpaEntity = travellerPersistenceMapper.travellerToTravellerJpaEntity(traveller);
        return travellerPersistenceMapper.travellerJpaEntityToTraveller(travellerRepository.save(travellerJpaEntity));
    }

    @Override
    public Traveller getTraveller(Long id) {
        return travellerPersistenceMapper.travellerJpaEntityToTraveller(
                travellerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.INVALID_TRAVELLER_ID)));
    }

    @Override
    public Traveller updateTraveller(Traveller traveller) {
        if(!travellerRepository.existsById(traveller.getId()))
            throw new ResourceNotFoundException(ResourceNotFoundException.INVALID_TRAVELLER_ID);

        TravellerJpaEntity travellerJpaEntity = travellerPersistenceMapper.travellerToTravellerJpaEntity(traveller);
        return travellerPersistenceMapper.travellerJpaEntityToTraveller(travellerRepository.save(travellerJpaEntity));
    }

    @Override
    public void deleteTraveller(Long id) {
        if(!travellerRepository.existsById(id))
            throw new ResourceNotFoundException(ResourceNotFoundException.INVALID_TRAVELLER_ID);

        travellerRepository.deleteById(id);
    }
}
