package ca.ulaval.glo4002.game.domain;

import ca.ulaval.glo4002.game.domain.action.*;
import ca.ulaval.glo4002.game.domain.dinosaur.AdultDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.BabyDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;
import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.Pantry;

import java.util.List;

public class Game {

    private final Turn turn;
    private final Herd herd;
    private final Pantry pantry;

    public Game(Herd herd, Pantry pantry, Turn turn) {
        this.herd = herd;
        this.pantry = pantry;
        this.turn = turn;
    }

    public void addAdultDinosaur(AdultDinosaur adultDinosaur) {
        ExecutableAction addAdultDinosaurAction = new AddAdultDinosaurAction(herd, adultDinosaur);
        turn.acquireNewAction(addAdultDinosaurAction);
    }

    public void addBabyDinosaur(BabyDinosaur babyDinosaur) {
        ExecutableAction addBabyDinosaurAction = new AddBabyDinosaurAction(herd, babyDinosaur);
        turn.acquireNewAction(addBabyDinosaurAction);
    }

    public void addFood(List<Food> foods) {
        ExecutableAction addFoodAction = new AddFoodAction(pantry, foods);
        turn.acquireNewAction(addFoodAction);
    }

    public void addSumoFight(Dinosaur dinosaurChallenger, Dinosaur dinosaurChallengee) {
        ExecutableAction addSumoFightAction = new SumoFightAction(herd, dinosaurChallenger, dinosaurChallengee);
        turn.acquireNewAction(addSumoFightAction);
    }

    public void modifyDinosaurWeight(int weightVariation, Dinosaur dinosaur) {
        ExecutableAction modifyWeightAction = new ModifyWeightAction(weightVariation, dinosaur);
        turn.acquireNewAction(modifyWeightAction);
    }

    public int playTurn() {
        int turnNumber = turn.playActions();

        pantry.incrementFreshFoodAges();
        pantry.storeAllNewlyOrderedFoods();
        pantry.splitWaterInTwo();
        herd.feedDinosaurs();
        pantry.mergeWater();

        herd.resetSumoFight();

        herd.increasingBabiesWeight();

        return turnNumber;
    }

    public void reset() {
        turn.reset();
        herd.reset();
        pantry.reset();
    }

    public Herd getHerd() {
        return this.herd;
    }

    public Pantry getPantry() {
        return this.pantry;
    }
}
