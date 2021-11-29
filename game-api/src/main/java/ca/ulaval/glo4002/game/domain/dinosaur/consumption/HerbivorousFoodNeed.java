package ca.ulaval.glo4002.game.domain.dinosaur.consumption;

public class HerbivorousFoodNeed implements FoodNeed {

    private final FoodConsumption foodConsumption = FoodConsumption.HERBIVOROUS;
    private final HerbivorousFoodStorage foodStorage;
    private final int saladNeeded;
    private final int waterNeeded;
    private boolean isSatisfied = false;

    public HerbivorousFoodNeed(HerbivorousFoodStorage foodStorage, int saladNeeded, int waterNeeded) {
        this.foodStorage = foodStorage;
        this.saladNeeded = saladNeeded;
        this.waterNeeded = waterNeeded;
    }

    @Override
    public FoodConsumption getFoodConsumption() {
        return foodConsumption;
    }

    @Override
    public void satisfy() {
        int saladConsumed = foodStorage.giveExactOrMostPossibleSaladDesired(saladNeeded);
        int waterConsumed = foodStorage.giveExactOrMostPossibleWaterDesiredToHerbivorous(waterNeeded);

        isSatisfied = saladNeeded == saladConsumed && waterNeeded == waterConsumed;
    }

    public boolean isSatisfied() {
        return isSatisfied;
    }
}
