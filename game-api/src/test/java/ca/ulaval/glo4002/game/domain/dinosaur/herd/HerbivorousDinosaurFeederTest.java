package ca.ulaval.glo4002.game.domain.dinosaur.herd;

import ca.ulaval.glo4002.game.domain.dinosaur.AdultDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Gender;
import ca.ulaval.glo4002.game.domain.dinosaur.Species;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumption;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumptionStrategy;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodNeed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class HerbivorousDinosaurFeederTest {

    private final static String HERBIVOROUS_NAME = "Bob";
    private final static String STRONGER_HERBIVOROUS_NAME = "Alfred";
    private final static int HERBIVOROUS_WEIGHT = 80;
    private final static int STRONGER_HERBIVOROUS_WEIGHT = 100;

    private HerbivorousDinosaurFeeder herbivorousDinosaurFeeder;

    private FoodNeed weakerDinosaurFoodNeed;
    private FoodNeed strongerDinosaurFoodNeed;
    private Map<Dinosaur, List<FoodNeed>> dinosaursWithNeed;

    @BeforeEach
    void setUp() {
        herbivorousDinosaurFeeder = new HerbivorousDinosaurFeeder(new WeakerToStrongerEatingOrder());

        FoodConsumptionStrategy herbivorousStrategy = mock(FoodConsumptionStrategy.class);
        FoodConsumptionStrategy strongerDinosaurHerbivorousStrategy = mock(FoodConsumptionStrategy.class);

        Dinosaur aHerbivorousDinosaur = new AdultDinosaur(Species.Allosaurus, HERBIVOROUS_WEIGHT, HERBIVOROUS_NAME,
                Gender.M, herbivorousStrategy);
        Dinosaur aStrongerHerbivorousDinosaur = new AdultDinosaur(Species.Allosaurus, STRONGER_HERBIVOROUS_WEIGHT,
                STRONGER_HERBIVOROUS_NAME, Gender.M, strongerDinosaurHerbivorousStrategy);

        weakerDinosaurFoodNeed = mock(FoodNeed.class);
        when(weakerDinosaurFoodNeed.getFoodConsumption()).thenReturn(FoodConsumption.HERBIVOROUS);
        strongerDinosaurFoodNeed = mock(FoodNeed.class);
        when(strongerDinosaurFoodNeed.getFoodConsumption()).thenReturn(FoodConsumption.HERBIVOROUS);

        dinosaursWithNeed = Map.of(aHerbivorousDinosaur, List.of(weakerDinosaurFoodNeed),
                aStrongerHerbivorousDinosaur, List.of(strongerDinosaurFoodNeed));
    }

    @Test
    public void whenFeedDinosaurs_thenFoodNeedFromWeakerDinosaursShouldBeSatisfiedFirst() {
        InOrder correctOrder = inOrder(weakerDinosaurFoodNeed, strongerDinosaurFoodNeed);

        herbivorousDinosaurFeeder.feedDinosaurs(dinosaursWithNeed);

        correctOrder.verify(weakerDinosaurFoodNeed).satisfy();
        correctOrder.verify(strongerDinosaurFoodNeed).satisfy();
    }
}
