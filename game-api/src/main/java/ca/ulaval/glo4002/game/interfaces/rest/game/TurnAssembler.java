package ca.ulaval.glo4002.game.interfaces.rest.game;

public class TurnAssembler {

    public TurnNumberDTO toDTO(int turnNumber) {
        return new TurnNumberDTO(turnNumber);
    }
}
