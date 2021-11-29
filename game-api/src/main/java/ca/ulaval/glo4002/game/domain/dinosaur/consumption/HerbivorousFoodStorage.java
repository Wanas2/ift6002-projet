package ca.ulaval.glo4002.game.domain.dinosaur.consumption;

public interface HerbivorousFoodStorage {

    int giveExactOrMostPossibleWaterDesiredToHerbivorous(int quantity);

    int giveExactOrMostPossibleSaladDesired(int quantity);
}
