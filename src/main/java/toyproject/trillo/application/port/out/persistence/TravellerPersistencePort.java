package toyproject.trillo.application.port.out.persistence;

import toyproject.trillo.domain.Traveller;

public interface TravellerPersistencePort {
    Traveller createTraveller(Traveller traveller);
    Traveller getTraveller(Long id);
    Traveller updateTraveller(Traveller traveller);
    void deleteTraveller(Long id);
}
