package ca.ulaval.glo4002.game.domain.food;

public enum FoodState {

    FRESH("Fresh"),
    CONSUMED("Consumed"),
    EXPIRED("Expired");

    private final String state;

    FoodState(String state) {
        this.state = state;
    }
}
