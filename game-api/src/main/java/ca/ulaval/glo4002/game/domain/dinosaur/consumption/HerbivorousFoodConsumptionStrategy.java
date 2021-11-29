package ca.ulaval.glo4002.game.domain.dinosaur.consumption;

import java.util.ArrayList;
import java.util.List;

public class HerbivorousFoodConsumptionStrategy implements FoodConsumptionStrategy {

    private final static int STARVING_FACTOR = 2;
    private final static int NON_STARVING_FACTOR = 1;
    private final static double WATER_FACTOR = 0.6;
    private final static double FOOD_FACTOR = 0.0025;

    private final HerbivorousFoodStorage herbivorousFoodStorage;
    private HerbivorousFoodNeed herbivorousFoodNeed;

    public HerbivorousFoodConsumptionStrategy(HerbivorousFoodStorage herbivorousFoodStorage) {
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
        return herbivorousFoodNeed == null || herbivorousFoodNeed.isSatisfied();
    }

    private List<FoodNeed> getFoodNeeds(int weight, int foodConsumptionFactor) {
        int waterNeeded = (int)Math.ceil(foodConsumptionFactor*weight*WATER_FACTOR);
        int saladNeeded = (int)Math.ceil(foodConsumptionFactor*weight*FOOD_FACTOR);
        herbivorousFoodNeed = new HerbivorousFoodNeed(herbivorousFoodStorage, saladNeeded, waterNeeded);

        List<FoodNeed> needs = new ArrayList<>();
        needs.add(herbivorousFoodNeed);
        return needs;
    }
}
