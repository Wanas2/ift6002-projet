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

public class CarnivorousDinosaurFeederTest {

    private final static String CARNIVOROUS_NAME = "Bob";
    private final static String STRONGER_CARNIVOROUS_NAME = "Alfred";
    private final static int CARNIVOROUS_WEIGHT = 80;
    private final static int STRONGER_CARNIVOROUS_WEIGHT = 100;

    private CarnivorousDinosaurFeeder carnivorousDinosaurFeeder;

    private FoodNeed weakerDinosaurFoodNeed;
    private FoodNeed strongerDinosaurFoodNeed;
    private Map<Dinosaur, List<FoodNeed>> dinosaursWithNeed;

    @BeforeEach
    void setUp() {
        carnivorousDinosaurFeeder = new CarnivorousDinosaurFeeder(new WeakerToStrongerEatingOrder());

        FoodConsumptionStrategy carnivorousStrategy = mock(FoodConsumptionStrategy.class);
        FoodConsumptionStrategy strongerDinosaurCarnivorousStrategy = mock(FoodConsumptionStrategy.class);

        Dinosaur aCarnivorousDinosaur = new AdultDinosaur(Species.Allosaurus, CARNIVOROUS_WEIGHT, CARNIVOROUS_NAME,
                Gender.M, carnivorousStrategy);
        Dinosaur aStrongerCarnivorousDinosaur = new AdultDinosaur(Species.Allosaurus, STRONGER_CARNIVOROUS_WEIGHT,
                STRONGER_CARNIVOROUS_NAME, Gender.M, strongerDinosaurCarnivorousStrategy);

        weakerDinosaurFoodNeed = mock(FoodNeed.class);
        when(weakerDinosaurFoodNeed.getFoodConsumption()).thenReturn(FoodConsumption.CARNIVOROUS);
        strongerDinosaurFoodNeed = mock(FoodNeed.class);
        when(strongerDinosaurFoodNeed.getFoodConsumption()).thenReturn(FoodConsumption.CARNIVOROUS);

        dinosaursWithNeed = Map.of(aCarnivorousDinosaur, List.of(weakerDinosaurFoodNeed),
                aStrongerCarnivorousDinosaur, List.of(strongerDinosaurFoodNeed));
    }

    @Test
    public void whenFeedDinosaurs_thenFoodNeedFromStrongerDinosaursShouldBeSatisfiedFirst() {
        InOrder correctOrder = inOrder(strongerDinosaurFoodNeed, weakerDinosaurFoodNeed);

        carnivorousDinosaurFeeder.feedDinosaurs(dinosaursWithNeed);

        correctOrder.verify(strongerDinosaurFoodNeed).satisfy();
        correctOrder.verify(weakerDinosaurFoodNeed).satisfy();
    }
}
