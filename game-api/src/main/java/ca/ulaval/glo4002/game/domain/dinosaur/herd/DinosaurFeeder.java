package ca.ulaval.glo4002.game.domain.dinosaur.herd;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodNeed;

import java.util.List;
import java.util.Map;

public interface DinosaurFeeder {

    void feedDinosaurs(Map<Dinosaur, List<FoodNeed>> dinosaursWithNeed);
}
