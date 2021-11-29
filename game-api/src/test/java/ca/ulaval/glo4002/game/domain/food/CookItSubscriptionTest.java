package ca.ulaval.glo4002.game.domain.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CookItSubscriptionTest {

    private final int EXPECTED_BURGER_QUANTITY = 100;
    private final int EXPECTED_SALAD_QUANTITY = 250;
    private final int EXPECTED_WATER_QUANTITY = 10000;

    private List<Food> foods;
    private FoodProvider cookItSubscription;

    @BeforeEach
    public void setUp() {
        cookItSubscription = new CookItSubscription();
        foods = new ArrayList<>();
    }

    @Test
    public void whenProvidingFood_thenFoodShouldBeProvided() {
        foods = cookItSubscription.provideFood();

        assertFalse(foods.isEmpty());
    }

    @Test
    public void whenProvidingFood_thenTheRightAmountOfFoodShouldBeProvided() {
        foods = cookItSubscription.provideFood();
        Optional<Food> burgers = foods.stream().
                filter(food->food.getType().equals(FoodType.BURGER)).
                findFirst();
        Optional<Food> salads = foods.stream().
                filter(food->food.getType().equals(FoodType.SALAD)).
                findFirst();
        Optional<Food> water = foods.stream().
                filter(food->food.getType().equals(FoodType.WATER)).
                findFirst();

        assertEquals(EXPECTED_BURGER_QUANTITY, burgers.get().quantity());
        assertEquals(EXPECTED_SALAD_QUANTITY, salads.get().quantity());
        assertEquals(EXPECTED_WATER_QUANTITY, water.get().quantity());
    }
}
