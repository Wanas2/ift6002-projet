package ca.ulaval.glo4002.game.domain;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.CarnivorousDinosaurFeeder;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.HerbivorousDinosaurFeeder;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.WeakerToStrongerEatingOrder;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.SumoFightOrganizer;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.SumoFightOrganizerValidator;
import ca.ulaval.glo4002.game.domain.food.CookItSubscription;
import ca.ulaval.glo4002.game.domain.food.FoodHistory;
import ca.ulaval.glo4002.game.domain.food.FoodProvider;
import ca.ulaval.glo4002.game.domain.food.Pantry;
import ca.ulaval.glo4002.game.domain.food.foodDistribution.FoodDistributor;
import ca.ulaval.glo4002.game.domain.food.foodDistribution.WaterSplitter;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {
    public Game createGame() {
        FoodProvider foodProvider = new CookItSubscription();
        FoodDistributor foodDistributor = new FoodDistributor();
        WaterSplitter waterSplitter = new WaterSplitter();
        FoodHistory foodHistory = new FoodHistory();
        Pantry pantry = new Pantry(foodProvider, foodDistributor, waterSplitter, foodHistory);

        WeakerToStrongerEatingOrder eatingOrder = new WeakerToStrongerEatingOrder();
        CarnivorousDinosaurFeeder carnivorousDinosaurFeeder = new CarnivorousDinosaurFeeder(eatingOrder);
        HerbivorousDinosaurFeeder herbivorousDinosaurFeeder = new HerbivorousDinosaurFeeder(eatingOrder);

        List<Dinosaur> dinosaurs = new ArrayList<>();
        SumoFightOrganizerValidator sumoFightOrganizerValidator = new SumoFightOrganizerValidator();
        SumoFightOrganizer sumoFightOrganizer = new SumoFightOrganizer(sumoFightOrganizerValidator);
        Herd herd = new Herd(dinosaurs, sumoFightOrganizer,
                List.of(carnivorousDinosaurFeeder, herbivorousDinosaurFeeder));

        Turn turn = new Turn();
        return new Game(herd, pantry, turn);
    }
}
