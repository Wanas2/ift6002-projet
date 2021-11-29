package ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BabyDinosaurResponseDTO {

    public final String gender;
    public final String offspring;

    @JsonCreator
    public BabyDinosaurResponseDTO(@JsonProperty(value = "gender", required = true) String gender,
                                   @JsonProperty(value = "offspring", required = true) String offspring) {
        this.gender = gender;
        this.offspring = offspring;
    }
}
