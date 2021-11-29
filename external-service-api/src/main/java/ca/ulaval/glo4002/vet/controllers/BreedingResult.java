package ca.ulaval.glo4002.vet.controllers;

import io.swagger.v3.oas.annotations.media.Schema;

public class BreedingResult {
    public final String offspring;
    @Schema(allowableValues = {"f", "m"})
    public final String gender;

    public BreedingResult(String offspring, String gender) {
        this.offspring = offspring;
        this.gender = gender;
    }
}
