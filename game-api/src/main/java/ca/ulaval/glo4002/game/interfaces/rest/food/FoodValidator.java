package ca.ulaval.glo4002.game.interfaces.rest.food;

import ca.ulaval.glo4002.game.interfaces.rest.food.dto.FoodDTO;

public class FoodValidator {

    public void validateFoodEntries(FoodDTO foodDTO) {
        if((foodDTO.qtyBurger < 0) || (foodDTO.qtySalad < 0) || (foodDTO.qtyWater < 0)) {
            throw new InvalidResourceQuantityException();
        }
    }
}
