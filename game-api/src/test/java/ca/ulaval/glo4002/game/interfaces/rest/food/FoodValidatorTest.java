package ca.ulaval.glo4002.game.interfaces.rest.food;

import ca.ulaval.glo4002.game.interfaces.rest.food.dto.FoodDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FoodValidatorTest {

    private final static int A_NEGATIVE_QUANTITY = -3;
    private final static int A_POSITIVE_QUANTITY_OF_BURGER = 4;
    private final static int A_POSITIVE_QUANTITY_OF_SALAD = 2;
    private final static int A_POSITIVE_QUANTITY_OF_WATER_IN_LITERS = 103;

    private FoodDTO foodDTO;
    private FoodValidator foodValidator;

    @BeforeEach
    void setUp() {
        foodValidator = new FoodValidator();
    }

    @Test
    public void givenANegativeQuantityOfBurger_whenValidateFoodEntries_thenShouldThrowException() {
        foodDTO = new FoodDTO(A_NEGATIVE_QUANTITY, A_POSITIVE_QUANTITY_OF_SALAD,
                A_POSITIVE_QUANTITY_OF_WATER_IN_LITERS);

        assertThrows(InvalidResourceQuantityException.class, ()->foodValidator.validateFoodEntries(foodDTO));
    }

    @Test
    public void givenANegativeQuantityOfSalad_whenValidateFoodEntries_thenShouldThrowException() {
        foodDTO = new FoodDTO(A_POSITIVE_QUANTITY_OF_BURGER, A_NEGATIVE_QUANTITY,
                A_POSITIVE_QUANTITY_OF_WATER_IN_LITERS);

        assertThrows(InvalidResourceQuantityException.class, ()->foodValidator.validateFoodEntries(foodDTO));
    }

    @Test
    public void givenANegativeQuantityOfWater_whenValidateFoodEntries_thenShouldThrowException() {
        foodDTO = new FoodDTO(A_POSITIVE_QUANTITY_OF_BURGER, A_POSITIVE_QUANTITY_OF_SALAD, A_NEGATIVE_QUANTITY);

        assertThrows(InvalidResourceQuantityException.class, ()->foodValidator.validateFoodEntries(foodDTO));
    }

    @Test
    public void givenCorrectQuantityOfFood_whenValidateFoodEntries_thenShouldNotThrowException() {
        foodDTO = new FoodDTO(A_POSITIVE_QUANTITY_OF_BURGER, A_POSITIVE_QUANTITY_OF_SALAD,
                A_POSITIVE_QUANTITY_OF_WATER_IN_LITERS);
        assertDoesNotThrow(()->foodValidator.validateFoodEntries(foodDTO));
    }
}
