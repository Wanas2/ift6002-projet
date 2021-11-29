package ca.ulaval.glo4002.game.domain.dinosaur.consumption;

import java.util.ArrayList;
import java.util.List;

public class OmnivorousFoodConsumptionStrategy implements FoodConsumptionStrategy {

    private final static int STARVING_FACTOR = 2;
    private final static int NON_STARVING_FACTOR = 1;
    private final static double WATER_FACTOR = 0.6;
    private final static double SALAD_FACTOR = 0.0025;
    private final static double BURGER_FACTOR = 0.001;

    private final CarnivorousFoodStorage carnivorousFoodStorage;
    private final HerbivorousFoodStorage herbivorousFoodStorage;
    private HerbivorousFoodNeed herbivorousFoodNeed;
    private CarnivorousFoodNeed carnivorousFoodNeed;

    public OmnivorousFoodConsumptionStrategy(CarnivorousFoodStorage carnivorousFoodStorage,
                                             HerbivorousFoodStorage herbivorousFoodStorage) {
        this.carnivorousFoodStorage = carnivorousFoodStorage;
        this.herbivorousFoodStorage = herbivorousFoodStorage;
    }

    @Override
    public List<FoodNeed> getNonStarvingFoodNeeds(int weight) {
        return getFoodNeeds(weight, NON_STARVING_FACTOR);
    }

    @Override
    public List<FoodNeed> getStarvingFoodNeeds(int weight) {
        return getFoodNeeds(weight, STARVING_FACTOR);
    }

    @Override
    public boolean areFoodNeedsSatisfied() {
        return (carnivorousFoodNeed == null || carnivorousFoodNeed.isSatisfied()) &&
                (herbivorousFoodNeed == null || herbivorousFoodNeed.isSatisfied());
    }

    private List<FoodNeed> getFoodNeeds(int weight, int foodConsumptionFactor) {
        int totalWaterNeeded = (int)Math.ceil(foodConsumptionFactor*weight*WATER_FACTOR);
        int waterNeeded = (int)Math.ceil(totalWaterNeeded/2.0);

        int saladNeeded = (int)Math.ceil(foodConsumptionFactor*weight*SALAD_FACTOR/2);
        herbivorousFoodNeed = new HerbivorousFoodNeed(herbivorousFoodStorage, saladNeeded, waterNeeded);

        int burgerNeeded = (int)Math.ceil(foodConsumptionFactor*weight*BURGER_FACTOR/2);
        carnivorousFoodNeed = new CarnivorousFoodNeed(carnivorousFoodStorage, burgerNeeded, waterNeeded);

        List<FoodNeed> needs = new ArrayList<>();
        needs.add(carnivorousFoodNeed);
        needs.add(herbivorousFoodNeed);
        return needs;
    }
}
