package ca.ulaval.glo4002.game.domain.dinosaur.consumption;

public interface CarnivorousFoodStorage {

    int giveExactOrMostPossibleWaterDesiredToCarnivorous(int quantity);

    int giveExactOrMostPossibleBurgerDesired(int quantity);
}
