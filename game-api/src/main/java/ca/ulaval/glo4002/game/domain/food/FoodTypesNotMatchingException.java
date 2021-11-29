package ca.ulaval.glo4002.game.domain.food;

public class FoodTypesNotMatchingException extends Exception {

    private final static String ERROR_MESSAGE = "Trying to add two foods whose types are different";

    public FoodTypesNotMatchingException() {
        super(ERROR_MESSAGE);
    }
}
