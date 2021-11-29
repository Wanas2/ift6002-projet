package ca.ulaval.glo4002.vet.controllers;

public class ImpossibleBreedingException extends RuntimeException {
    public ImpossibleBreedingException(String fatherSpecies, String motherSpecies) {
        super("Cannot breed with a " + fatherSpecies + " father and a " + motherSpecies + " mother.");
    }
}
