package ca.ulaval.glo4002.game.domain.dinosaur.consumption;

public enum FoodConsumption {
    CARNIVOROUS(1.5f),
    HERBIVOROUS(1.0f),
    OMNIVOROUS(1.5f);

    private final float consumptionFactor;

    FoodConsumption(float consumptionFactor) {
        this.consumptionFactor = consumptionFactor;
    }

    public float getConsumptionFactor() {
        return consumptionFactor;
    }
}
