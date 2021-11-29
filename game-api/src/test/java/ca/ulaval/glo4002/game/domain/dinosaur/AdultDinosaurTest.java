package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumptionStrategy;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightChangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class AdultDinosaurTest {

    private final static int A_POSITIVE_WEIGHT_VARIATION = 27;
    private final static int DINOSAUR_WEIGHT = 150;
    private final static String DINOSAUR_NAME = "Bobi";

    private AdultDinosaur aDinosaur;

    @BeforeEach
    public void setup() {
        FoodConsumptionStrategy aFoodConsumptionStrategy = mock(FoodConsumptionStrategy.class);
        aDinosaur = new AdultDinosaur(Species.Ankylosaurus, DINOSAUR_WEIGHT, DINOSAUR_NAME, Gender.F,
                aFoodConsumptionStrategy);
    }

    @Test
    void givenAPositiveWeightVariation_whenModifyWeight_thenTheWeightVariationShouldBeAddedToTheDinosaurWeight() {
        int dinosaurWeightAfterWeightIncrease = aDinosaur.getWeight()+A_POSITIVE_WEIGHT_VARIATION;

        aDinosaur.modifyWeight(A_POSITIVE_WEIGHT_VARIATION);
        int newDinosaurWeight = aDinosaur.getWeight();

        assertEquals(dinosaurWeightAfterWeightIncrease, newDinosaurWeight);
    }

    @Test
    public void givenANegativeWeightVariationMoreThanACurrentWeight_whenModifyWeight_ThenShouldThrowInvalidWeightChangeException() {
        int weightVariation = -(DINOSAUR_WEIGHT+1);

        assertThrows(InvalidWeightChangeException.class,
                ()->aDinosaur.validateWeightVariation(weightVariation)
        );
    }
}
