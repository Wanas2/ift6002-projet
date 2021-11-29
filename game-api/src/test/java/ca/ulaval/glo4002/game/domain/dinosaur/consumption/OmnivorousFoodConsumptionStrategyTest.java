package ca.ulaval.glo4002.game.domain.dinosaur.consumption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class OmnivorousFoodConsumptionStrategyTest {

    private final static int WEIGHT = 81;
    private final static int EXPECTED_NORMAL_SALADS = 1;
    private final static int EXPECTED_NORMAL_BURGERS = 1;
    private final static int EXPECTED_NORMAL_WATER = 25;
    private final static int EXPECTED_DOUBLE_SALADS = 1;
    private final static int EXPECTED_DOUBLE_BURGERS = 1;
    private final static int EXPECTED_DOUBLE_WATER = 49;
    private final static int NUMBER_OF_NEEDS = 2;

    private OmnivorousFoodConsumptionStrategy strategy;
    private HerbivorousFoodStorage herbivorousFoodStorage;
    private CarnivorousFoodStorage carnivorousFoodStorage;

    @BeforeEach
    public void setup() {
        carnivorousFoodStorage = mock(CarnivorousFoodStorage.class);
        herbivorousFoodStorage = mock(HerbivorousFoodStorage.class);
        strategy = new OmnivorousFoodConsumptionStrategy(carnivorousFoodStorage, herbivorousFoodStorage);
    }

    @Test
    public void whenGetNonStarvingFoodNeeds_thenFoodNeedsShouldBeOneCarnivorousAndOneHerbivorousNeed() {
        List<FoodNeed> foodNeeds = strategy.getNonStarvingFoodNeeds(WEIGHT);

        assertEquals(NUMBER_OF_NEEDS, foodNeeds.size());
        assertTrue(foodNeeds.stream()
                .anyMatch((foodNeed->foodNeed.getFoodConsumption() == FoodConsumption.CARNIVOROUS)));
        assertTrue(foodNeeds.stream()
                .anyMatch((foodNeed->foodNeed.getFoodConsumption() == FoodConsumption.HERBIVOROUS)));
    }

    @Test
    public void whenGetStarvingFoodNeeds_thenFoodNeedsShouldBeOneCarnivorousAndOneHerbivorousNeed() {
        List<FoodNeed> foodNeeds = strategy.getStarvingFoodNeeds(WEIGHT);

        assertEquals(NUMBER_OF_NEEDS, foodNeeds.size());
        assertTrue(foodNeeds.stream()
                .anyMatch((foodNeed->foodNeed.getFoodConsumption() == FoodConsumption.CARNIVOROUS)));
        assertTrue(foodNeeds.stream()
                .anyMatch((foodNeed->foodNeed.getFoodConsumption() == FoodConsumption.HERBIVOROUS)));
    }

    @Test
    public void whenGetNonStarvingFoodNeeds_thenFoodNeedsShouldTakeTheRightAmount() {
        List<FoodNeed> foodNeeds = strategy.getNonStarvingFoodNeeds(WEIGHT);

        foodNeeds.forEach(FoodNeed::satisfy);
        verify(carnivorousFoodStorage).giveExactOrMostPossibleBurgerDesired(EXPECTED_NORMAL_BURGERS);
        verify(carnivorousFoodStorage).giveExactOrMostPossibleWaterDesiredToCarnivorous(EXPECTED_NORMAL_WATER);
        verify(herbivorousFoodStorage).giveExactOrMostPossibleWaterDesiredToHerbivorous(EXPECTED_NORMAL_WATER);
        verify(herbivorousFoodStorage).giveExactOrMostPossibleSaladDesired(EXPECTED_NORMAL_SALADS);
    }

    @Test
    public void whenGetStarvingFoodNeeds_thenFoodNeedsShouldTakeTheDoubleOfRightAmount() {
        List<FoodNeed> foodNeeds = strategy.getStarvingFoodNeeds(WEIGHT);

        foodNeeds.forEach(FoodNeed::satisfy);
        verify(carnivorousFoodStorage).giveExactOrMostPossibleBurgerDesired(EXPECTED_DOUBLE_BURGERS);
        verify(carnivorousFoodStorage).giveExactOrMostPossibleWaterDesiredToCarnivorous(EXPECTED_DOUBLE_WATER);
        verify(herbivorousFoodStorage).giveExactOrMostPossibleWaterDesiredToHerbivorous(EXPECTED_DOUBLE_WATER);
        verify(herbivorousFoodStorage).giveExactOrMostPossibleSaladDesired(EXPECTED_DOUBLE_SALADS);
    }

    @Test
    public void givenFoodNeedsAreSatisfied_whenAreFoodNeedsSatisfied_thenShouldBeSatisfied() {
        when(herbivorousFoodStorage.giveExactOrMostPossibleSaladDesired(EXPECTED_NORMAL_SALADS))
                .thenReturn(EXPECTED_NORMAL_SALADS);
        when(herbivorousFoodStorage.giveExactOrMostPossibleWaterDesiredToHerbivorous(EXPECTED_NORMAL_WATER))
                .thenReturn(EXPECTED_NORMAL_WATER);
        when(carnivorousFoodStorage.giveExactOrMostPossibleBurgerDesired(EXPECTED_NORMAL_BURGERS))
                .thenReturn(EXPECTED_NORMAL_BURGERS);
        when(carnivorousFoodStorage.giveExactOrMostPossibleWaterDesiredToCarnivorous(EXPECTED_NORMAL_WATER))
                .thenReturn(EXPECTED_NORMAL_WATER);
        List<FoodNeed> foodNeeds = strategy.getNonStarvingFoodNeeds(WEIGHT);
        foodNeeds.forEach(FoodNeed::satisfy);

        boolean areFoodNeedsSatisfied = strategy.areFoodNeedsSatisfied();

        assertTrue(areFoodNeedsSatisfied);
    }
}
