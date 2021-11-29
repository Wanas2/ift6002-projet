package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.assembler;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto.SumoResponseDTO;

public class SumoAssembler {

    public SumoResponseDTO toDTO(String predictedWinner) {
        return new SumoResponseDTO(predictedWinner);
    }
}
