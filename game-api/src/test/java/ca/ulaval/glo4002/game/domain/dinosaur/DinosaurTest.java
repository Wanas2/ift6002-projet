package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumptionStrategy;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodNeed;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidBabyWeightChangeException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightChangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DinosaurTest {

    private final static int DINOSAUR_WEIGHT = 87;
    private final static int STRONGER_DINOSAUR_WEIGHT = 9999;
    private final static int WEAKER_THAN = -1;

    private FoodNeed foodNeed;
    private Dinosaur aDinosaur;
    private Dinosaur aStrongerDinosaur;
    private FoodConsumptionStrategy aFoodConsumptionStrategy;

    @BeforeEach
    public void setup() {
        foodNeed = mock(FoodNeed.class);
        aFoodConsumptionStrategy = mock(FoodConsumptionStrategy.class);
        String aDinosaurName = "Bobi";
        String anotherDinosaurName = "Bob";
        aDinosaur = new Dinosaur(Species.Ankylosaurus, DINOSAUR_WEIGHT, aDinosaurName, Gender.F,
                aFoodConsumptionStrategy, DinosaurStage.ADULT);
        aStrongerDinosaur = new Dinosaur(Species.Ankylosaurus, STRONGER_DINOSAUR_WEIGHT, anotherDinosaurName,
                Gender.F, aFoodConsumptionStrategy, DinosaurStage.ADULT);
    }

    @Test
    public void givenADinosaurWithFoodNeedsNotSatisfied_whenIsAlive_thenDinosaurShouldNotBeALive() {
        when(aFoodConsumptionStrategy.areFoodNeedsSatisfied()).thenReturn(false);

        boolean isAlive = aDinosaur.isAlive();

        assertFalse(isAlive);
    }

    @Test
    public void givenADinosaurWithFoodNeedsSatisfied_whenIsAlive_thenDinosaurShouldBeAlive() {
        when(aFoodConsumptionStrategy.areFoodNeedsSatisfied()).thenReturn(true);

        boolean isAlive = aDinosaur.isAlive();

        assertTrue(isAlive);
    }

    @Test
    public void whenLoseFight_thenDinosaurShouldNotBeAlive() {
        aDinosaur.loseFight();

        assertFalse(aDinosaur.isAlive());
    }

    @Test
    public void whenWinFight_thenDinosaurShouldBeStarving() {
        aDinosaur.winFight();

        aDinosaur.askForFood();
        verify(aFoodConsumptionStrategy).getStarvingFoodNeeds(anyInt());
    }

    @Test
    public void givenDinosaurIsStarving_whenAskForFood_thenDinosaurShouldGetStarvingFoodNeed() {
        aDinosaur.askForFood();

        verify(aFoodConsumptionStrategy).getStarvingFoodNeeds(DINOSAUR_WEIGHT);
    }

    @Test
    public void givenDinosaurIsNotStarving_whenAskForFood_thenDinosaurShouldGetNormalFoodNeed() {
        aDinosaur.askForFood();

        aDinosaur.askForFood();

        verify(aFoodConsumptionStrategy).getNonStarvingFoodNeeds(DINOSAUR_WEIGHT);
    }

    @Test
    public void whenAskForFood_thenShouldReturnFoodNeedList() {
        List<FoodNeed> foodNeeds = new ArrayList<>(Collections.singleton(foodNeed));
        when(aFoodConsumptionStrategy.getStarvingFoodNeeds(DINOSAUR_WEIGHT)).thenReturn(foodNeeds);

        List<FoodNeed> foodNeedsReturned = aDinosaur.askForFood();

        assertEquals(foodNeeds, foodNeedsReturned);
    }

    @Test
    public void givenAStrongerDinosaur_whenCompareStrength_thenDinosaurShouldBeWeakerThanTheStronger() {
        int strengthComparison = aDinosaur.compareStrength(aStrongerDinosaur);

        assertEquals(WEAKER_THAN, strengthComparison);
    }

    @Test
    public void givenABabyDinosaur_whenValidateWeightVariation_thenShouldThrowInvalidBabyWeightChangeException() {
        Dinosaur aBabyDinosaur = givenABabyDinosaur();
        final int aWeightVariation = 10;

        assertThrows(InvalidBabyWeightChangeException.class, () ->
                aBabyDinosaur.validateWeightVariation(aWeightVariation));
    }

    @Test
    public void givenAdultDinosaur_whenValidateWeightVariationUnder100_thenShouldThrowInvalidWeightChangeException() {
        Dinosaur adultDinosaur = givenAdultDinosaur();
        final int invalidWeightVariation = -100;

        assertThrows(InvalidWeightChangeException.class, () ->
                adultDinosaur.validateWeightVariation(invalidWeightVariation));
    }

    @Test
    public void givenAdultDinosaur_whenModifyWeight_thenTheWeightShouldBeChanged() {
        Dinosaur adultDinosaur = givenAdultDinosaur();
        final int weightToAdd = 10;
        final int expectedWeight = DINOSAUR_WEIGHT + weightToAdd;

        adultDinosaur.modifyWeight(weightToAdd);

        assertEquals(expectedWeight, adultDinosaur.getWeight());
    }

    @Test
    public void givenABabyDinosaur_WhenIncreaseWeight_thenTheWeightShouldShouldBeIncresed() {
        Dinosaur aBabyDinosaur = givenABabyDinosaur();
        int expectedWeight = 1 + 33;

        aBabyDinosaur.increaseWeight();

        assertEquals(expectedWeight, aBabyDinosaur.getWeight());
    }

    @Test
    public void givenABabyDinosaur_WhenIncreaseWeightMoreThan100_thenTheDinosaurShouldBecomeAdult() {
        Dinosaur aBabyDinosaur = givenABabyDinosaur();

        whenExceedWeight(aBabyDinosaur);

        assertEquals(DinosaurStage.ADULT, aBabyDinosaur.getDinosaurStage());
    }

    @Test
    public void givenABabyDinosaur_WhenHisTwoParentsDie_thenShouldDie() {
        Dinosaur aBabyDinosaur = givenABabyDinosaurWithDeathParents();

        assertFalse(aBabyDinosaur.isAlive());
    }

    private Dinosaur givenABabyDinosaur() {
        return new Dinosaur(Species.Ankylosaurus, "name", Gender.M, mock(FoodConsumptionStrategy.class),
                mock(Dinosaur.class), mock(Dinosaur.class), DinosaurStage.BABY);
    }

    private Dinosaur givenABabyDinosaurWithDeathParents() {
        Dinosaur father = mock(Dinosaur.class);
        Dinosaur mother = mock(Dinosaur.class);

        when(father.isAlive()).thenReturn(false);
        when(mother.isAlive()).thenReturn(false);

        return new Dinosaur(Species.Ankylosaurus, "name", Gender.M, mock(FoodConsumptionStrategy.class),
                father, mother, DinosaurStage.BABY);
    }

    private Dinosaur givenAdultDinosaur() {
        return  new Dinosaur(Species.Ankylosaurus, DINOSAUR_WEIGHT, "name", Gender.M,
                mock(FoodConsumptionStrategy.class), DinosaurStage.ADULT);
    }

    private void whenExceedWeight(Dinosaur dinosaur) {
        dinosaur.increaseWeight();
        dinosaur.increaseWeight();
        dinosaur.increaseWeight();
        dinosaur.increaseWeight();
    }
}
