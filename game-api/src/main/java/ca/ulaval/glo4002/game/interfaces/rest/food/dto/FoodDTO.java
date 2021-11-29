package ca.ulaval.glo4002.game.interfaces.rest.food.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodDTO {

    public final int qtyBurger;
    public final int qtySalad;
    public final int qtyWater;

    @JsonCreator
    public FoodDTO(@JsonProperty(value = "qtyBurger") int qtyBurger,
                   @JsonProperty(value = "qtySalad") int qtySalad,
                   @JsonProperty(value = "qtyWater") int qtyWater) {
        this.qtyBurger = qtyBurger;
        this.qtySalad = qtySalad;
        this.qtyWater = qtyWater;
    }
}
