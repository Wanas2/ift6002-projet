package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumptionStrategy;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodNeed;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidBabyWeightChangeException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightChangeException;

import java.util.List;

public class Dinosaur {
    private final static int MINIMUM_WEIGHT = 100;

    private final static int INITIAL_BABY_WEIGHT = 1;
    private final static int WEIGHT_INCREASE_PER_TURN = 33;
    private final static int WEIGHT_TO_BECOME_ADULT = 100;

    private final Species species;
    protected int weight;
    private final String name;
    private final Gender gender;
    protected final FoodConsumptionStrategy foodConsumptionStrategy;

    private boolean isAlive = true;
    protected boolean isStarving = true;

    private Dinosaur fatherDinosaur;
    private Dinosaur motherDinosaur;

    private DinosaurStage dinosaurStage;

    public Dinosaur(Species species, int weight, String name, Gender gender,
                    FoodConsumptionStrategy foodConsumptionStrategy, DinosaurStage dinosaurStage) {
        this.species = species;
        this.weight = weight;
        this.name = name;
        this.gender = gender;
        this.foodConsumptionStrategy = foodConsumptionStrategy;
        this.dinosaurStage = dinosaurStage;
    }

    public Dinosaur(Species species, String name, Gender gender,
                        FoodConsumptionStrategy foodConsumptionStrategy, Dinosaur fatherDinosaur,
                        Dinosaur motherDinosaur, DinosaurStage dinosaurStage) {
        this(species, INITIAL_BABY_WEIGHT, name, gender, foodConsumptionStrategy, dinosaurStage);
        this.fatherDinosaur = fatherDinosaur;
        this.motherDinosaur = motherDinosaur;
    }

    public boolean isAlive() {
        boolean isAlive = this.isAlive && foodConsumptionStrategy.areFoodNeedsSatisfied();
        if(dinosaurStage.equals(DinosaurStage.BABY)) {
            return isAlive && this.fatherDinosaur.isAlive() && this.motherDinosaur.isAlive();
        }
        return isAlive;
    }

    public void validateWeightVariation(int weightVariation) {
        if(dinosaurStage.equals(DinosaurStage.BABY)) {
            throw new InvalidBabyWeightChangeException();
        }

        if(dinosaurStage.equals(DinosaurStage.ADULT)) {
            if(this.weight + weightVariation < MINIMUM_WEIGHT) {
                throw new InvalidWeightChangeException();
            }
        }
    }

    public void modifyWeight(int weightVariation) {
        if(dinosaurStage.equals(DinosaurStage.ADULT)) {
            this.weight = this.weight + weightVariation;
        }
    }

    public void increaseWeight() {
        if(dinosaurStage.equals(DinosaurStage.BABY)) {
            this.weight += WEIGHT_INCREASE_PER_TURN;
            if(this.weight >= WEIGHT_TO_BECOME_ADULT) {
                dinosaurStage = DinosaurStage.ADULT;
            }
        }
    }

    public List<FoodNeed> askForFood() {
        List<FoodNeed> foodNeeds = isStarving ? foodConsumptionStrategy.getStarvingFoodNeeds(weight) :
                foodConsumptionStrategy.getNonStarvingFoodNeeds(weight);
        isStarving = false;
        return foodNeeds;
    }

    public void loseFight() {
        isAlive = false;
    }

    public void winFight() {
        isStarving = true;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public Gender getGender() {
        return gender;
    }

    public Species getSpecies() {
        return species;
    }

    public int compareStrength(Dinosaur dinosaur) {
        return Integer.compare(this.calculateStrength(), dinosaur.calculateStrength());
    }

    private int calculateStrength() {
        return (int)Math.ceil(weight*gender.getGenderFactor()*species.getConsumptionStrength());
    }
}
