package ca.ulaval.glo4002.game.interfaces.rest.dinosaur;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GrowDTO {

    public final int weight;

    @JsonCreator
    public GrowDTO(@JsonProperty(value = "weight", required = true) int weight) {
        this.weight = weight;
    }
}
