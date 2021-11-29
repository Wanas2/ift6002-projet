package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumptionStrategy;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidBabyWeightChangeException;

import java.util.Optional;

public class BabyDinosaur extends Dinosaur {

    private final static int INITIAL_BABY_WEIGHT = 1;
    private final static int WEIGHT_INCREASE_PER_TURN = 33;
    private final static int WEIGHT_TO_BECOME_ADULT = 100;

    protected final Dinosaur fatherDinosaur;
    protected final Dinosaur motherDinosaur;

    public BabyDinosaur(Species species, String name, Gender gender,
                        FoodConsumptionStrategy foodConsumptionStrategy, Dinosaur fatherDinosaur,
                        Dinosaur motherDinosaur) {
        super(species, INITIAL_BABY_WEIGHT, name, gender, foodConsumptionStrategy);
        this.fatherDinosaur = fatherDinosaur;
        this.motherDinosaur = motherDinosaur;
    }

    @Override
    public boolean isAlive() {
        return super.isAlive() && (fatherDinosaur.isAlive() || motherDinosaur.isAlive());
    }

    @Override
    public void modifyWeight(int weightValue) {
    }

    public void increaseWeight() {
        if(this.weight < WEIGHT_TO_BECOME_ADULT) {
            this.weight += WEIGHT_INCREASE_PER_TURN;
        }
    }

    public Optional<AdultDinosaur> becomeAdult() {
        Optional<AdultDinosaur> potentialAdultDinosaur = Optional.empty();
        if(this.weight >= WEIGHT_TO_BECOME_ADULT) {
            AdultDinosaur adultDinosaur = new AdultDinosaur(this.getSpecies(), this.weight,
                    this.getName(), this.getGender(), this.foodConsumptionStrategy);
            adultDinosaur.isStarving = false;
            potentialAdultDinosaur = Optional.of(adultDinosaur);
        }

        return potentialAdultDinosaur;
    }

    @Override
    public void validateWeightVariation(int weightVariation) {
        throw new InvalidBabyWeightChangeException();
    }
}
