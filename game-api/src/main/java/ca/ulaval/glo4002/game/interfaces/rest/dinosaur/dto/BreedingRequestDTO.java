package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BreedingRequestDTO {

    public final String name;
    public final String fatherName;
    public final String motherName;

    @JsonCreator
    public BreedingRequestDTO(@JsonProperty(value = "name", required = true) String name,
                              @JsonProperty(value = "fatherName", required = true) String fatherName,
                              @JsonProperty(value = "motherName", required = true) String motherName) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }
}
