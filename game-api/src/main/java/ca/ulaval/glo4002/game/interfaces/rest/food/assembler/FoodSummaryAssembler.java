package ca.ulaval.glo4002.game.interfaces.rest.food.assembler;

import ca.ulaval.glo4002.game.domain.food.FoodHistory;
import ca.ulaval.glo4002.game.interfaces.rest.food.dto.FoodDTO;
import ca.ulaval.glo4002.game.interfaces.rest.food.dto.FoodSummaryDTO;

public class FoodSummaryAssembler {

    private final FoodAssembler foodAssembler;

    public FoodSummaryAssembler(FoodAssembler foodAssembler) {
        this.foodAssembler = foodAssembler;
    }

    public FoodSummaryDTO toDTO(FoodHistory foodHistory) {
        FoodDTO freshFoodDTO = foodAssembler.toDTO(foodHistory.getFreshFoodQuantities());
        FoodDTO expiredFoodDTO = foodAssembler.toDTO(foodHistory.getExpiredFoodQuantities());
        FoodDTO consumedFoodDTO = foodAssembler.toDTO(foodHistory.getConsumedFoodQuantities());
        return new FoodSummaryDTO(freshFoodDTO, expiredFoodDTO, consumedFoodDTO);
    }
}
