package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.consumption.*;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidGenderException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidMotherException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidSpeciesException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightException;

public class DinosaurFactory {

    private final CarnivorousFoodStorage carnivorousFoodStorage;
    private final HerbivorousFoodStorage herbivorousFoodStorage;

    public DinosaurFactory(CarnivorousFoodStorage carnivorousFoodStorage,
                           HerbivorousFoodStorage herbivorousFoodStorage) {
        this.carnivorousFoodStorage = carnivorousFoodStorage;
        this.herbivorousFoodStorage = herbivorousFoodStorage;
    }

    public AdultDinosaur createAdultDinosaur(String genderName, int weight, String speciesName, String name) {
        if(weight < 100) {
            throw new InvalidWeightException();
        }

        Gender gender = findCorrespondingGender(genderName);
        Species species = findCorrespondingSpecies(speciesName);
        FoodConsumptionStrategy foodConsumptionStrategy = findCorrespondingFoodConsumptionStrategy(species);

        return new AdultDinosaur(species, weight, name, gender, foodConsumptionStrategy);
    }

    public BabyDinosaur createBaby(String genderName, String speciesName, String name, Dinosaur fatherDinosaur,
                                   Dinosaur motherDinosaur) {
        validateParentsGender(fatherDinosaur, motherDinosaur);
        Gender gender = findCorrespondingGender(genderName);
        Species species = findCorrespondingSpecies(speciesName);
        FoodConsumptionStrategy foodConsumptionStrategy = findCorrespondingFoodConsumptionStrategy(species);

        return new BabyDinosaur(species, name, gender, foodConsumptionStrategy, fatherDinosaur, motherDinosaur);
    }

    private void validateParentsGender(Dinosaur fatherDinosaur, Dinosaur motherDinosaur) {
        if(fatherDinosaur.getGender() != Gender.M) {
            throw new InvalidFatherException();
        }
        if(motherDinosaur.getGender() != Gender.F) {
            throw new InvalidMotherException();
        }
    }

    private Gender findCorrespondingGender(String gender) {
        gender = gender.toUpperCase();
        try {
            return Gender.valueOf(gender);
        } catch(IllegalArgumentException e) {
            throw new InvalidGenderException();
        }
    }

    private Species findCorrespondingSpecies(String species) {
        species = species.replace(" ", "");
        try {
            return Species.valueOf(species);
        } catch(IllegalArgumentException e) {
            throw new InvalidSpeciesException();
        }
    }

    private FoodConsumptionStrategy findCorrespondingFoodConsumptionStrategy(Species species) {
        FoodConsumptionStrategy foodConsumptionStrategy;
        switch(species.getConsumptionType()) {
            case CARNIVOROUS:
                foodConsumptionStrategy = new CarnivorousFoodConsumptionStrategy(carnivorousFoodStorage);
                break;
            case HERBIVOROUS:
                foodConsumptionStrategy = new HerbivorousFoodConsumptionStrategy(herbivorousFoodStorage);
                break;
            case OMNIVOROUS:
                foodConsumptionStrategy = new OmnivorousFoodConsumptionStrategy(carnivorousFoodStorage,
                        herbivorousFoodStorage);
                break;
            default:
                throw new InvalidSpeciesException();
        }
        return foodConsumptionStrategy;
    }
}
