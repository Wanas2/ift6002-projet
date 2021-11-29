package ca.ulaval.glo4002.game.domain.dinosaur.consumption;

public class CarnivorousFoodNeed implements FoodNeed {

    private final FoodConsumption foodConsumption = FoodConsumption.CARNIVOROUS;
    private final CarnivorousFoodStorage foodStorage;
    private final int burgerNeeded;
    private final int waterNeeded;
    private boolean isSatisfied = false;

    public CarnivorousFoodNeed(CarnivorousFoodStorage foodStorage, int burgerNeeded, int waterNeeded) {
        this.foodStorage = foodStorage;
        this.burgerNeeded = burgerNeeded;
        this.waterNeeded = waterNeeded;
    }

    @Override
    public FoodConsumption getFoodConsumption() {
        return foodConsumption;
    }

    @Override
    public void satisfy() {
        int burgerConsumed = foodStorage.giveExactOrMostPossibleBurgerDesired(burgerNeeded);
        int waterConsumed = foodStorage.giveExactOrMostPossibleWaterDesiredToCarnivorous(waterNeeded);

        isSatisfied = burgerNeeded == burgerConsumed && waterNeeded == waterConsumed;
    }

    public boolean isSatisfied() {
        return isSatisfied;
    }
}
