package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionDTO {

    @JsonProperty(value = "error")
    public final String error;

    @JsonProperty(value = "description")
    public final String description;

    public ExceptionDTO(String error, String description) {
        this.error = error;
        this.description = description;
    }
}
