package ca.ulaval.glo4002.game.domain.dinosaur.consumption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class HerbivorousFoodConsumptionStrategyTest {

    private final static int WEIGHT = 81;
    private final static int EXPECTED_NORMAL_SALADS = 1;
    private final static int EXPECTED_NORMAL_WATER = 49;
    private final static int EXPECTED_DOUBLE_SALADS = 1;
    private final static int EXPECTED_DOUBLE_WATER = 98;
    private final static int NUMBER_OF_NEEDS = 1;

    private HerbivorousFoodConsumptionStrategy strategy;
    private HerbivorousFoodStorage foodStorage;

    @BeforeEach
    public void setup() {
        foodStorage = mock(HerbivorousFoodStorage.class);
        strategy = new HerbivorousFoodConsumptionStrategy(foodStorage);
    }

    @Test
    public void whenGetNonStarvingFoodNeeds_thenFoodNeedsShouldBeOnlyOneCarnivorousNeed() {
        List<FoodNeed> foodNeeds = strategy.getNonStarvingFoodNeeds(WEIGHT);

        assertEquals(NUMBER_OF_NEEDS, foodNeeds.size());
        assertEquals(FoodConsumption.HERBIVOROUS, foodNeeds.get(0).getFoodConsumption());
    }

    @Test
    public void whenGetStarvingFoodNeeds_thenFoodNeedsShouldBeOnlyOneCarnivorousNeed() {
        List<FoodNeed> foodNeeds = strategy.getStarvingFoodNeeds(WEIGHT);

        assertEquals(NUMBER_OF_NEEDS, foodNeeds.size());
        assertEquals(FoodConsumption.HERBIVOROUS, foodNeeds.get(0).getFoodConsumption());
    }

    @Test
    public void whenGetNonStarvingFoodNeeds_thenFoodNeedsShouldTakeTheRightAmount() {
        List<FoodNeed> foodNeeds = strategy.getNonStarvingFoodNeeds(WEIGHT);

        foodNeeds.forEach(FoodNeed::satisfy);
        verify(foodStorage).giveExactOrMostPossibleSaladDesired(EXPECTED_NORMAL_SALADS);
        verify(foodStorage).giveExactOrMostPossibleWaterDesiredToHerbivorous(EXPECTED_NORMAL_WATER);
    }

    @Test
    public void whenGetStarvingFoodNeeds_thenFoodNeedsShouldTakeTheDoubleOfRightAmount() {
        List<FoodNeed> foodNeeds = strategy.getStarvingFoodNeeds(WEIGHT);

        foodNeeds.forEach(FoodNeed::satisfy);
        verify(foodStorage).giveExactOrMostPossibleSaladDesired(EXPECTED_DOUBLE_SALADS);
        verify(foodStorage).giveExactOrMostPossibleWaterDesiredToHerbivorous(EXPECTED_DOUBLE_WATER);
    }

    @Test
    public void givenFoodNeedsAreSatisfied_whenAreFoodNeedsSatisfied_thenShouldBeSatisfied() {
        when(foodStorage.giveExactOrMostPossibleSaladDesired(EXPECTED_NORMAL_SALADS))
                .thenReturn(EXPECTED_NORMAL_SALADS);
        when(foodStorage.giveExactOrMostPossibleWaterDesiredToHerbivorous(EXPECTED_NORMAL_WATER))
                .thenReturn(EXPECTED_NORMAL_WATER);
        List<FoodNeed> foodNeeds = strategy.getNonStarvingFoodNeeds(WEIGHT);
        foodNeeds.forEach(FoodNeed::satisfy);

        boolean areFoodNeedsSatisfied = strategy.areFoodNeedsSatisfied();

        assertTrue(areFoodNeedsSatisfied);
    }
}
