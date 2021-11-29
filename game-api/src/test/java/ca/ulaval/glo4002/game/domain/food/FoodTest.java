package ca.ulaval.glo4002.game.domain.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    private final static FoodType FOOD_TYPE_1 = FoodType.BURGER;
    private final static FoodType FOOD_TYPE_2 = FoodType.SALAD;
    private final static int FOOD_QUANTITY = 4;

    private Food food;
    private Food foodOfADifferentType;
    private Food foodExpiringIn4Turns;

    @BeforeEach
    void setUp() {
        food = new Food(FOOD_TYPE_1, FOOD_QUANTITY);
        foodOfADifferentType = new Food(FOOD_TYPE_2, FOOD_QUANTITY);
    }

    @Test
    public void initiallyFoodIsFresh() {
        assertFalse(food.isExpired());
    }

    @Test
    public void givenAFoodWhichExpireIn4Turns_whenIncrementAgeByOne_thenFoodShouldExpire() {
        foodExpiringIn4Turns = new Food(FOOD_TYPE_1, FOOD_QUANTITY);

        foodExpiringIn4Turns.incrementAgeByOne();
        foodExpiringIn4Turns.incrementAgeByOne();
        foodExpiringIn4Turns.incrementAgeByOne();
        foodExpiringIn4Turns.incrementAgeByOne();

        assertTrue(foodExpiringIn4Turns.isExpired());
    }

    @Test
    public void givenAFoodQuantityToAdd_whenIncreaseQuantity_thenTheQuantityShouldBeIncreased() {
        int foodQuantityToAdd = 5;
        int expectedFoodQuantity = 9;

        food.increaseQuantity(foodQuantityToAdd);

        assertEquals(expectedFoodQuantity, food.quantity());
    }

    @Test
    public void givenFoodOfADifferentTypeQuantityToAdd_whenIncreaseQuantity_thenExceptionShouldBeThrown() {
        assertThrows(FoodTypesNotMatchingException.class, ()->food.increaseQuantity(foodOfADifferentType));
    }

    @Test
    public void givenAFoodAndAFoodQuantityToDecrease_whenDecreaseQuantity_thenQuantityShouldBeDecreased() {
        int foodQuantityToDecrease = 2;
        int expectedFoodQuantity = FOOD_QUANTITY-foodQuantityToDecrease;

        food.decreaseQuantity(foodQuantityToDecrease);

        assertEquals(expectedFoodQuantity, food.quantity());
    }

    @Test
    public void givenAQuantityGreaterOrEqualThanTheFoodQuantity_whenDecreaseQuantity_thenTheRemainingFoodQuantityShouldBeZero() {
        int foodQuantityToDecrease = FOOD_QUANTITY+2;
        int expectedRemainingFoodQuantity = 0;

        food.decreaseQuantity(foodQuantityToDecrease);

        assertEquals(expectedRemainingFoodQuantity, food.quantity());
    }
}
