package ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BreedingRequestExternalDTO {

    public final String fatherSpecies;
    public final String motherSpecies;

    @JsonCreator
    public BreedingRequestExternalDTO(String fatherSpecies, String motherSpecies) {
        this.fatherSpecies = fatherSpecies;
        this.motherSpecies = motherSpecies;
    }
}
