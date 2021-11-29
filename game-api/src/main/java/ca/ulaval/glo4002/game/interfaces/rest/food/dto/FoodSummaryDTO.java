package ca.ulaval.glo4002.game.interfaces.rest.food.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class FoodSummaryDTO {

    public final FoodDTO fresh;
    public final FoodDTO expired;
    public final FoodDTO consumed;

    @JsonCreator
    public FoodSummaryDTO(FoodDTO fresh, FoodDTO expired, FoodDTO consumed) {
        this.fresh = fresh;
        this.expired = expired;
        this.consumed = consumed;
    }
}
