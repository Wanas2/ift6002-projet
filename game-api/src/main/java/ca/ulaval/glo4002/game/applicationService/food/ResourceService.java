package ca.ulaval.glo4002.game.applicationService.food;

import ca.ulaval.glo4002.game.domain.Game;
import ca.ulaval.glo4002.game.domain.GameFactory;
import ca.ulaval.glo4002.game.domain.GameRepository;
import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.FoodHistory;
import ca.ulaval.glo4002.game.domain.food.Pantry;

import java.util.List;

public class ResourceService {

    private final GameRepository gameRepository;
    private final GameFactory gameFactory;

    public ResourceService(GameRepository gameRepository, GameFactory gameFactory) {
        this.gameRepository = gameRepository;
        this.gameFactory = gameFactory;
    }

    public void addFood(List<Food> food) {
        Game game = gameRepository.find()
                .orElse(gameFactory.createGame());
        game.addFood(food);
    }

    public FoodHistory getFoodQuantitySummary() {
        Game game = gameRepository.find()
                .orElse(gameFactory.createGame());
        Pantry pantry = game.getPantry();
        return pantry.obtainFoodHistory();
    }
}
