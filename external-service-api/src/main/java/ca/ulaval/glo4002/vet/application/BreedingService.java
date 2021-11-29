package ca.ulaval.glo4002.vet.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.ulaval.glo4002.vet.controllers.ImpossibleBreedingException;
import ca.ulaval.glo4002.vet.domain.Breeding;
import ca.ulaval.glo4002.vet.repositories.BreedingRepository;

@Service
public class BreedingService {
    private final BreedingRepository repository;

    @Autowired
    public BreedingService(BreedingRepository repository) {
        this.repository = repository;
    }

    public Breeding breed(String fatherSpecies, String motherSpecies) {
        Breeding breeding = repository.findByParents(fatherSpecies, motherSpecies);

        if (breeding == null) {
            throw new ImpossibleBreedingException(fatherSpecies, motherSpecies);
        }

        return breeding;
    }
}
