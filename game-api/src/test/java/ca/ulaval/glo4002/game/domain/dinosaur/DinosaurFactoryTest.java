package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.consumption.CarnivorousFoodStorage;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumptionStrategy;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.HerbivorousFoodStorage;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidGenderException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidMotherException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidSpeciesException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class DinosaurFactoryTest {

    private final static String A_NAME = "Bobi";
    private final static int A_WEIGHT = 176;
    private final static String A_GENDER = "f";
    private final static String A_SPECIES = "Ankylosaurus";

    private DinosaurFactory dinosaurFactory;
    private Dinosaur fatherDinosaur;
    private Dinosaur motherDinosaur;

    @BeforeEach
    public void setup() {
        fatherDinosaur = createAMaleDinosaur();
        motherDinosaur = createAFemaleDinosaur();

        CarnivorousFoodStorage carnivorousFoodStorage = mock(CarnivorousFoodStorage.class);
        HerbivorousFoodStorage herbivorousFoodStorage = mock(HerbivorousFoodStorage.class);
        dinosaurFactory = new DinosaurFactory(carnivorousFoodStorage, herbivorousFoodStorage);
    }

    @Test
    public void givenAGenderNeitherMNorF_whenCreateAdultDinosaur_thenShouldThrowInvalidGenderException() {
        String anInvalidGender = "X";

        assertThrows(InvalidGenderException.class,
                ()->dinosaurFactory.createAdultDinosaur(anInvalidGender, A_WEIGHT, A_SPECIES, A_NAME));
    }

    @Test
    public void givenAnInvalidSpecies_whenCreateAdultDinosaur_thenShouldThrowInvalidSpeciesException() {
        String anInvalidSpecies = "Labrador";

        assertThrows(InvalidSpeciesException.class,
                ()->dinosaurFactory.createAdultDinosaur(A_GENDER, A_WEIGHT, anInvalidSpecies, A_NAME));
    }

    @Test
    public void givenAnInvalidWeight_whenCreateAdultDinosaur_thenShouldThrowInvalidWeightException() {
        int anInvalidWeight = -1;

        assertThrows(InvalidWeightException.class,
                ()->dinosaurFactory.createAdultDinosaur(A_GENDER, anInvalidWeight, A_SPECIES, A_NAME));
    }

    @Test
    public void givenCorrectParameters_whenCreateAdultDinosaur_thenShouldNotThrow() {
        assertDoesNotThrow(()->dinosaurFactory.createAdultDinosaur(A_GENDER, A_WEIGHT, A_SPECIES, A_NAME));
    }

    @Test
    public void givenAFatherDinosaurNotMale_whenCreateBabyDinosaur_thenShouldThrowInvalidFatherException() {
        fatherDinosaur = createAFemaleDinosaur();

        assertThrows(InvalidFatherException.class,
                ()->dinosaurFactory.createBaby(A_GENDER, A_SPECIES, A_NAME, fatherDinosaur, motherDinosaur));
    }

    @Test
    public void givenAMotherDinosaurNotFemale_whenCreateBabyDinosaur_thenShouldThrowInvalidMotherException() {
        motherDinosaur = createAMaleDinosaur();

        assertThrows(InvalidMotherException.class,
                ()->dinosaurFactory.createBaby(A_GENDER, A_SPECIES, A_NAME, fatherDinosaur, motherDinosaur));
    }

    @Test
    public void givenCorrectParameters_whenCreateBabyDinosaur_thenShouldNotThrow() {
        assertDoesNotThrow(
                ()->dinosaurFactory.createBaby(A_GENDER, A_SPECIES, A_NAME, fatherDinosaur, motherDinosaur));
    }

    private Dinosaur createAMaleDinosaur() {
        FoodConsumptionStrategy foodConsumptionStrategy = mock(FoodConsumptionStrategy.class);
        return new AdultDinosaur(Species.Spinosaurus, 7, "Joe", Gender.M, foodConsumptionStrategy);
    }

    private Dinosaur createAFemaleDinosaur() {
        FoodConsumptionStrategy foodConsumptionStrategy = mock(FoodConsumptionStrategy.class);
        return new AdultDinosaur(Species.Ankylosaurus, 7, "Berta", Gender.F, foodConsumptionStrategy);
    }
}
