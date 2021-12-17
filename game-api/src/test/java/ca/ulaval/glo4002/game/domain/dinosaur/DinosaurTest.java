package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumptionStrategy;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodNeed;
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
}
