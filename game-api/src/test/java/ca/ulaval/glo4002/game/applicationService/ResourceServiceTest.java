package ca.ulaval.glo4002.game.applicationService;

import ca.ulaval.glo4002.game.applicationService.food.ResourceService;
import ca.ulaval.glo4002.game.domain.Game;
import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.FoodType;
import ca.ulaval.glo4002.game.domain.food.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class ResourceServiceTest {

    private final static int A_QUANTITY_OF_BURGER_ORDERED = 100;
    private final static int A_QUANTITY_OF_SALAD_ORDERED = 250;
    private final static int A_QUANTITY_OF_WATER_ORDERED = 10;

    private List<Food> someFoodCreated;

    private Game game;
    private Pantry pantry;
    private ResourceService resourceService;

    @BeforeEach
    void setUp() {
        pantry = mock(Pantry.class);

        game = mock(Game.class);
        resourceService = new ResourceService(game);
    }

    @Test
    public void givenCreatedFood_whenAddFood_thenShouldAddTheAppropriateFood() {
        initializeSomeFood();

        resourceService.addFood(someFoodCreated);

        verify(game).addFood(someFoodCreated);
    }

    @Test
    public void whenGetFoodQuantitySummary_thenSummaryShouldBeCalculated() {
        when(game.getPantry()).thenReturn(pantry);

        resourceService.getFoodQuantitySummary();

        verify(pantry).obtainFoodHistory();
    }

    private void initializeSomeFood() {
        Food aFoodItem1 = new Food(FoodType.BURGER, A_QUANTITY_OF_BURGER_ORDERED);
        Food aFoodItem2 = new Food(FoodType.SALAD, A_QUANTITY_OF_SALAD_ORDERED);
        Food aFoodItem3 = new Food(FoodType.WATER, A_QUANTITY_OF_WATER_ORDERED);
        someFoodCreated = List.of(aFoodItem1, aFoodItem2, aFoodItem3);
    }
}
