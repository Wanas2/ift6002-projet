package ca.ulaval.glo4002.game.domain.food;

import ca.ulaval.glo4002.game.domain.food.foodDistribution.FoodDistributor;
import ca.ulaval.glo4002.game.domain.food.foodDistribution.WaterSplitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PantryTest {

    private static final FoodType A_FOOD_TYPE_1 = FoodType.BURGER;
    private static final FoodType A_FOOD_TYPE_2 = FoodType.SALAD;
    private static final int A_FOOD_QUANTITY_1 = 12;
    private static final int A_FOOD_QUANTITY_2 = 5;

    private Food food1;
    private Food food2;
    private Food foodExpiringAfterMoreThanThreeTurns;
    private List<Food> orderedFoods;
    private FoodHistory foodHistory;
    private FoodDistributor foodDistributor;
    private WaterSplitter waterSplitter;
    private FoodProvider foodProvider;
    private Pantry pantry;

    @BeforeEach
    void setUp() {
        food1 = new Food(A_FOOD_TYPE_1, A_FOOD_QUANTITY_1);
        food2 = new Food(A_FOOD_TYPE_2, A_FOOD_QUANTITY_2);
        orderedFoods = new ArrayList<>();
        foodExpiringAfterMoreThanThreeTurns = new Food(A_FOOD_TYPE_1, A_FOOD_QUANTITY_2);
        foodProvider = mock(CookItSubscription.class);
        foodDistributor = mock(FoodDistributor.class);
        waterSplitter = mock(WaterSplitter.class);
        foodHistory = mock(FoodHistory.class);
        pantry = new Pantry(foodProvider, foodDistributor, waterSplitter, foodHistory);
    }

    @Test
    public void initiallyNoFoodIsStoredInPantry() {
        assertTrue(pantry.getAllFreshFoods().isEmpty());
    }

    @Test
    public void givenSomeFood_whenObtainNewlyOrderedFood_thenPantryStillHasNoFreshFood() {
        orderedFoods.add(food1);
        orderedFoods.add(food2);

        pantry.obtainNewlyOrderedFoods(orderedFoods);

        assertTrue(pantry.getAllFreshFoods().isEmpty());
    }

    @Test
    public void givenSomeFoodOrdered_whenStoreAllNewlyOrderedFoods_thenPantryNowHasFreshFood() {
        orderedFoods.add(food1);
        orderedFoods.add(food2);

        pantry.obtainNewlyOrderedFoods(orderedFoods);
        pantry.storeAllNewlyOrderedFoods();

        assertFalse(pantry.getAllFreshFoods().isEmpty());
    }

    @Test
    public void whenStoreAllNewlyOrderedFoods_thenTheFoodProviderShouldProvideFoodToPantry() {
        pantry.storeAllNewlyOrderedFoods();

        verify(foodProvider).provideFood();
    }

    @Test
    public void givenSomeFoodOrdered_whenStoreAllNewlyOrderedFoods_thenBothOrderedFoodAreAllStoredAsFreshFood() {
        orderedFoods.add(food1);
        Food anotherFood = new Food(FoodType.BURGER, A_FOOD_QUANTITY_2);
        when(foodProvider.provideFood()).thenReturn(List.of(anotherFood));
        int expectedQuantityOfFreshFood = food1.quantity()+anotherFood.quantity();

        pantry.obtainNewlyOrderedFoods(orderedFoods);
        pantry.storeAllNewlyOrderedFoods();
        int quantityOfFreshFood = pantry.getAllFreshFoods().stream().mapToInt(Food::quantity).sum();

        assertEquals(expectedQuantityOfFreshFood, quantityOfFreshFood);
    }

    @Test
    public void whenGiveExactOrMostPossibleSaladDesired_thenShouldDistributeTheFoodAsked() {
        pantry.giveExactOrMostPossibleSaladDesired(A_FOOD_QUANTITY_1);

        verify(foodDistributor).distributeExactOrMostPossibleFoodAsked(FoodType.SALAD,
                pantry.getAllFreshFoods(), A_FOOD_QUANTITY_1, foodHistory);
    }

    @Test
    public void whenGiveExactOrMostPossibleBurgerDesired_thenShouldDistributeTheFoodAsked() {
        pantry.giveExactOrMostPossibleBurgerDesired(A_FOOD_QUANTITY_1);

        verify(foodDistributor).distributeExactOrMostPossibleFoodAsked(FoodType.BURGER,
                pantry.getAllFreshFoods(), A_FOOD_QUANTITY_1, foodHistory);
    }

    @Test
    public void whenGiveExactOrMostPossibleWaterDesiredForCarnivorous_thenShouldDistributeTheFoodAsked() {
        pantry.giveExactOrMostPossibleWaterDesiredToCarnivorous(A_FOOD_QUANTITY_1);

        verify(foodDistributor).distributeExactOrMostPossibleFoodAsked(FoodType.WATER,
                pantry.getAllFreshFoods(), A_FOOD_QUANTITY_1, foodHistory);
    }

    @Test
    public void whenGiveExactOrMostPossibleWaterDesiredForHerbivorous_thenShouldDistributeTheFoodAsked() {
        pantry.giveExactOrMostPossibleWaterDesiredToHerbivorous(A_FOOD_QUANTITY_1);

        verify(foodDistributor).distributeExactOrMostPossibleFoodAsked(FoodType.WATER,
                pantry.getAllFreshFoods(), A_FOOD_QUANTITY_1, foodHistory);
    }

    @Test
    public void whenObtainFoodHistory_thenShouldComputeFreshFoodQuantities() {
        pantry.obtainFoodHistory();

        verify(foodHistory).computeFreshFoodQuantities(pantry.getAllFreshFoods());
    }

    @Test
    public void whenMergeWater_thenWaterShouldBeMerged() {
        pantry.mergeWater();

        verify(waterSplitter).mergeWater(pantry.getAllFreshFoods());
    }

    @Test
    public void givenSomeExpiredFood_whenIncrementFreshFoodAges_ThenTheQuantityOfTheExpiredFoodShouldBeIncremented() {
        orderedFoods.add(foodExpiringAfterMoreThanThreeTurns);
        pantry.obtainNewlyOrderedFoods(orderedFoods);
        pantry.storeAllNewlyOrderedFoods();

        Food storedFreshFood = pantry.getAllFreshFoods().get(0);
        pantry.incrementFreshFoodAges();
        pantry.incrementFreshFoodAges();
        pantry.incrementFreshFoodAges();
        pantry.incrementFreshFoodAges();

        verify(foodHistory).increaseExpiredQuantity(storedFreshFood);
    }

    @Test
    public void givenSomeNonExpiredFood_whenIncrementFreshFoodAges_ThenTheQuantityOfTheExpiredFoodShouldNotBeIncremented() {
        orderedFoods.add(foodExpiringAfterMoreThanThreeTurns);
        pantry.obtainNewlyOrderedFoods(orderedFoods);
        pantry.storeAllNewlyOrderedFoods();

        Food storedFreshFood = pantry.getAllFreshFoods().get(0);
        pantry.incrementFreshFoodAges();
        pantry.incrementFreshFoodAges();

        verify(foodHistory, never()).increaseExpiredQuantity(storedFreshFood);
    }

    @Test
    public void givenSomeFreshFood_whenSplitWaterInTwo_TheTheWaterIsSplit() {
        pantry.splitWaterInTwo();

        verify(waterSplitter).splitWater(pantry.getAllFreshFoods());
    }

    @Test
    public void givenSomeFreshFoodStored_whenReset_thenPantryHasNoFreshFoodLeft() {
        orderedFoods.add(food1);
        pantry.obtainNewlyOrderedFoods(orderedFoods);
        pantry.storeAllNewlyOrderedFoods();

        pantry.reset();

        assertTrue(pantry.getAllFreshFoods().isEmpty());
    }

    @Test
    public void whenReset_thenHistoryShouldBeReset() {
        pantry.reset();

        verify(foodHistory).reset();
    }
}
