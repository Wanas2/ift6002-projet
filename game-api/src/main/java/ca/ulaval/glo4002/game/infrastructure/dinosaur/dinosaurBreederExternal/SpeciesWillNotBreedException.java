package ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal;

public class SpeciesWillNotBreedException extends Exception {

    private static final String MESSAGE = "Impossible to breed these species";

    public SpeciesWillNotBreedException() {
        super(MESSAGE);
    }
}
