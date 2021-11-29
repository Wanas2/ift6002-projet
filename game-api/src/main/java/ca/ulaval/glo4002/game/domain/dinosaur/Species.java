package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumption;

public enum Species {
    Ankylosaurus(FoodConsumption.HERBIVOROUS, "Ankylosaurus"),
    Brachiosaurus(FoodConsumption.HERBIVOROUS, "Brachiosaurus"),
    Diplodocus(FoodConsumption.HERBIVOROUS, "Diplodocus"),
    Stegosaurus(FoodConsumption.HERBIVOROUS, "Stegosaurus"),
    Triceratops(FoodConsumption.HERBIVOROUS, "Triceratops"),
    Allosaurus(FoodConsumption.CARNIVOROUS, "Allosaurus"),
    Megalosaurus(FoodConsumption.CARNIVOROUS, "Megalosaurus"),
    Spinosaurus(FoodConsumption.CARNIVOROUS, "Spinosaurus"),
    TyrannosaurusRex(FoodConsumption.CARNIVOROUS, "Tyrannosaurus Rex"),
    Velociraptor(FoodConsumption.CARNIVOROUS, "Velociraptor"),
    Eoraptor(FoodConsumption.OMNIVOROUS, "Eoraptor"),
    Gigantoraptor(FoodConsumption.OMNIVOROUS, "Gigantoraptor"),
    Heterodontosaurus(FoodConsumption.OMNIVOROUS, "Heterodontosaurus"),
    Ornithomimus(FoodConsumption.OMNIVOROUS, "Ornithomimus"),
    Struthiomimus(FoodConsumption.OMNIVOROUS, "Struthiomimus");

    private final FoodConsumption consumptionType;
    private final String name;

    Species(FoodConsumption consumptionType, String name) {
        this.consumptionType = consumptionType;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public FoodConsumption getConsumptionType() {
        return consumptionType;
    }

    public float getConsumptionStrength() {
        return consumptionType.getConsumptionFactor();
    }
}
