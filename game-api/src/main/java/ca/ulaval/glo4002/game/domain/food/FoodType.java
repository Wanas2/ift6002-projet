package ca.ulaval.glo4002.game.domain.food;

public enum FoodType {

    BURGER("Burger", 4),
    SALAD("Salade", 3),
    WATER("Eau", 10);

    private final String name;
    private final int numberOfTurnBeforeExpiry;

    FoodType(String name, int numberOfTurnBeforeExpiry) {
        this.name = name;
        this.numberOfTurnBeforeExpiry = numberOfTurnBeforeExpiry;
    }

    @Override
    public String toString() {
        return name;
    }

    public int numberOfTurnBeforeExpiry() {
        return numberOfTurnBeforeExpiry;
    }
}
