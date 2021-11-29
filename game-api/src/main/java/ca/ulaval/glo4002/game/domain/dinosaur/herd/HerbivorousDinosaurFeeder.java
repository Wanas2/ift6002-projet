package ca.ulaval.glo4002.game.domain.dinosaur.herd;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumption;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodNeed;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HerbivorousDinosaurFeeder implements DinosaurFeeder {

    private final WeakerToStrongerEatingOrder eatingOrder;

    public HerbivorousDinosaurFeeder(WeakerToStrongerEatingOrder eatingOrder) {
        this.eatingOrder = eatingOrder;
    }

    @Override
    public void feedDinosaurs(Map<Dinosaur, List<FoodNeed>> dinosaursWithNeed) {
        List<FoodNeed> herbivorousFoodNeedFromWeakerToStronger = dinosaursWithNeed.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(eatingOrder::compareDinosaurEatingOrder))
                .flatMap(entry->entry.getValue().stream())
                .filter(foodNeed->foodNeed.getFoodConsumption() == FoodConsumption.HERBIVOROUS)
                .collect(Collectors.toList());

        herbivorousFoodNeedFromWeakerToStronger.forEach(FoodNeed::satisfy);
    }
}
