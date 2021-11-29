package ca.ulaval.glo4002.game.domain.food.foodDistribution;

import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.FoodHistory;
import ca.ulaval.glo4002.game.domain.food.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FoodDistributorTest {

    private static final FoodType A_FOOD_TYPE_TO_PROVIDE = FoodType.BURGER;
    private static final FoodType ANOTHER_FOOD_TYPE_TO_PROVIDE = FoodType.SALAD;

    private List<Food> allFreshFoods;
    private FoodHistory foodHistory;
    private FoodDistributor foodDistributor;

    @BeforeEach
    void setUp() {
        allFreshFoods = new LinkedList<>();
        foodHistory = mock(FoodHistory.class);
        foodDistributor = new FoodDistributor();
    }

    @Test
    public void givenARequestedFoodType_whenDistributeExactOrMostPossibleFoodAsked_thenTheRequestedFoodTypeIsGiven() {
        int requestedFoodQuantity = 4;
        int quantityOfAvailableFreshBurgersBeforeGiving = 10;
        int quantityOfAvailableFreshSaladsBeforeGiving = 15;
        int expectedRemainingBurgerQuantity = quantityOfAvailableFreshBurgersBeforeGiving-requestedFoodQuantity;
        Food freshBurgers = new Food(A_FOOD_TYPE_TO_PROVIDE, quantityOfAvailableFreshBurgersBeforeGiving);
        Food freshSalads = new Food(ANOTHER_FOOD_TYPE_TO_PROVIDE, quantityOfAvailableFreshSaladsBeforeGiving);
        allFreshFoods.add(freshBurgers);
        allFreshFoods.add(freshSalads);

        foodDistributor.distributeExactOrMostPossibleFoodAsked(A_FOOD_TYPE_TO_PROVIDE, allFreshFoods,
                requestedFoodQuantity, foodHistory);
        int remainingBurgerQuantity = allFreshFoods.get(0).quantity();
        int remainingSaladQuantity = allFreshFoods.get(1).quantity();

        assertEquals(expectedRemainingBurgerQuantity, remainingBurgerQuantity);
        assertEquals(quantityOfAvailableFreshSaladsBeforeGiving, remainingSaladQuantity);
    }

    @Test
    public void givenZeroFoodQuantityRequested_whenDistributeExactOrMostPossibleFoodAsked_thenNoZeroIsGiven() {
        int requestedFoodQuantity = 0;
        int expectedGivenFoodQuantity = 0;

        int totalFoodGivenQuantity = foodDistributor.distributeExactOrMostPossibleFoodAsked(A_FOOD_TYPE_TO_PROVIDE,
                allFreshFoods, requestedFoodQuantity, foodHistory);

        assertEquals(expectedGivenFoodQuantity, totalFoodGivenQuantity);
    }

    @Test
    public void givenQuantityOfFoodRequestedIsLessThenOrEqualToThatAvailableOfFreshFoods_whenDistributeExactOrMostPossibleFoodAsked_thenQuantityRequestedIsGiven() {
        int requestedFoodQuantity = 5;
        int quantityOfAvailableFreshFood = 10;

        Food food = new Food(A_FOOD_TYPE_TO_PROVIDE, quantityOfAvailableFreshFood);
        allFreshFoods.add(food);
        int totalFoodGivenQuantity = foodDistributor.distributeExactOrMostPossibleFoodAsked(A_FOOD_TYPE_TO_PROVIDE,
                allFreshFoods, requestedFoodQuantity, foodHistory);

        assertEquals(requestedFoodQuantity, totalFoodGivenQuantity);
    }

    @Test
    public void givenQuantityOfFoodRequestedIsLessThenOrEqualToThatAvailableOfFreshFoods_whenDistributeExactOrMostPossibleFoodAsked_thenTheConsumedFoodFoodQuantityIsIncreased() {
        int requestedFoodQuantity = 5;
        int quantityOfAvailableFreshFood = 10;

        Food food = new Food(A_FOOD_TYPE_TO_PROVIDE, quantityOfAvailableFreshFood);
        allFreshFoods.add(food);
        foodDistributor.distributeExactOrMostPossibleFoodAsked(A_FOOD_TYPE_TO_PROVIDE, allFreshFoods,
                requestedFoodQuantity, foodHistory);

        verify(foodHistory).increaseConsumedQuantity(any(Food.class));
    }

    @Test
    public void givenQuantityOfFoodRequestedIsGreaterThenThatOfAvailableFreshFoods_whenDistributeExactOrMostPossibleFoodAsked_thenQuantityOfAvailableFreshFoodIsGiven() {
        int requestedFoodQuantity = 10;
        int quantityOfAvailableFreshFood = 5;

        Food food = new Food(A_FOOD_TYPE_TO_PROVIDE, quantityOfAvailableFreshFood);
        allFreshFoods.add(food);
        int totalFoodGivenQuantity = foodDistributor.distributeExactOrMostPossibleFoodAsked(A_FOOD_TYPE_TO_PROVIDE,
                allFreshFoods, requestedFoodQuantity, foodHistory);

        assertEquals(quantityOfAvailableFreshFood, totalFoodGivenQuantity);
    }

    @Test
    public void givenQuantityOfFoodRequestedIsGreaterThenThatFirstBatchOfFreshFood_whenDistributeExactOrMostPossibleFoodAsked_thenFoodIsGottenFromOtherBatchesOfFreshFood() {
        int requestedFoodQuantity = 12;
        int quantityOfAvailableFreshBurgersInFirstBatch = 5;
        int quantityOfAvailableFreshBurgersInSecondBatch = 10;
        int expectedRemainingFoodQuantity = quantityOfAvailableFreshBurgersInFirstBatch+
                quantityOfAvailableFreshBurgersInSecondBatch
                -requestedFoodQuantity;
        allFreshFoods.add(new Food(A_FOOD_TYPE_TO_PROVIDE, quantityOfAvailableFreshBurgersInFirstBatch));
        allFreshFoods.add(new Food(A_FOOD_TYPE_TO_PROVIDE, quantityOfAvailableFreshBurgersInSecondBatch));

        foodDistributor.distributeExactOrMostPossibleFoodAsked(A_FOOD_TYPE_TO_PROVIDE, allFreshFoods,
                requestedFoodQuantity, foodHistory);

        int remainingFoodQuantityAfterGiving = allFreshFoods.stream()
                .filter(foodFiltered->foodFiltered.getType().equals(A_FOOD_TYPE_TO_PROVIDE))
                .mapToInt(Food::quantity).sum();

        assertEquals(expectedRemainingFoodQuantity, remainingFoodQuantityAfterGiving);
    }

    @Test
    public void givenQuantityOfFoodRequestedIsGreaterThenThatOfAvailableFreshFoods_whenDistributeExactOrMostPossibleFoodAsked_thenTheConsumedFoodFoodQuantityIsIncreasedWithTheQuantityOfTheGivenFood() {
        int requestedFoodQuantity = 15;
        int quantityOfAvailableFreshFood = 10;

        Food food = new Food(A_FOOD_TYPE_TO_PROVIDE, quantityOfAvailableFreshFood);
        allFreshFoods.add(food);
        foodDistributor.distributeExactOrMostPossibleFoodAsked(A_FOOD_TYPE_TO_PROVIDE, allFreshFoods,
                requestedFoodQuantity, foodHistory);

        verify(foodHistory).increaseConsumedQuantity(food);
    }
}
