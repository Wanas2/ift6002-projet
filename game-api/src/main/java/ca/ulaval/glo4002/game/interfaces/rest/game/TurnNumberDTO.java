package ca.ulaval.glo4002.game.interfaces.rest.game;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TurnNumberDTO {

    public final int turnNumber;

    @JsonCreator
    public TurnNumberDTO(int turnNumber) {
        this.turnNumber = turnNumber;
    }
}
