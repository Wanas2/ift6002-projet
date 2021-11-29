package ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.dto.BreedingRequestExternalDTO;

public class BreedingAssembler {

    public BreedingRequestExternalDTO toDTO(Dinosaur fatherDinosaur, Dinosaur motherDinosaur) {
        String fatherSpecies = fatherDinosaur.getSpecies().toString();
        String motherSpecies = motherDinosaur.getSpecies().toString();
        return new BreedingRequestExternalDTO(fatherSpecies, motherSpecies);
    }
}
