package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.assembler;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto.DinosaurDTO;

public class DinosaurAssembler {

    public DinosaurDTO toDTO(Dinosaur dinosaur) {
        String gender = dinosaur.getGender().name().toLowerCase();
        String species = dinosaur.getSpecies().getName();
        return new DinosaurDTO(dinosaur.getName(), dinosaur.getWeight(), gender, species);
    }
}
