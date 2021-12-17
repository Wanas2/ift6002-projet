package ca.ulaval.glo4002.game.applicationService.food;

import ca.ulaval.glo4002.game.domain.Game;
import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.FoodHistory;

import java.util.List;

public class ResourceService {

    private final Game game;

    public ResourceService(Game game) {
        this.game = game;
    }

    public void addFood(List<Food> food) {
        game.addFood(food);
    }

    public FoodHistory getFoodQuantitySummary() {
        return game.getPantry().obtainFoodHistory();
    }
}
