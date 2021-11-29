package ca.ulaval.glo4002.game.applicationService;

import ca.ulaval.glo4002.game.applicationService.food.ResourceService;
import ca.ulaval.glo4002.game.domain.Game;
import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.FoodHistory;
import ca.ulaval.glo4002.game.domain.food.FoodType;
import ca.ulaval.glo4002.game.domain.food.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ResourceServiceTest {

    private final static int A_QUANTITY_OF_BURGER_ORDERED = 100;
    private final static int A_QUANTITY_OF_SALAD_ORDERED = 250;
    private final static int A_QUANTITY_OF_WATER_IN_LITERS_ORDERED = 10;

    private Food aFoodItem1;
    private Food aFoodItem2;
    private Food aFoodItem3;
    private List<Food> someFoodCreated;
    private FoodHistory foodHistory;
    private Pantry pantry;
    private Game game;
    private ResourceService resourceService;

    @BeforeEach
    void setUp() {
        foodHistory = mock(FoodHistory.class);
        pantry = mock(Pantry.class);
        game = mock(Game.class);
        resourceService = new ResourceService(pantry, game);
    }

    @Test
    public void givenCreatedFood_whenAddFood_thenShouldAddTheAppropriateFood() {
        initializeSomeFood();

        resourceService.addFood(someFoodCreated);

        verify(game).addFood(someFoodCreated);
    }

    @Test
    public void whenGetFoodQuantitySummary_thenSummaryShouldBeCalculated() {
        resourceService.getFoodQuantitySummary();

        verify(pantry).obtainFoodHistory();
    }

    private void initializeSomeFood() {
        aFoodItem1 = new Food(FoodType.BURGER, A_QUANTITY_OF_BURGER_ORDERED);
        aFoodItem2 = new Food(FoodType.SALAD, A_QUANTITY_OF_SALAD_ORDERED);
        aFoodItem3 = new Food(FoodType.WATER, A_QUANTITY_OF_WATER_IN_LITERS_ORDERED);
        someFoodCreated = new ArrayList<>();
        someFoodCreated.add(aFoodItem1);
        someFoodCreated.add(aFoodItem2);
        someFoodCreated.add(aFoodItem3);
    }
}
