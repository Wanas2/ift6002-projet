package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightChangeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidWeightChangeExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<InvalidWeightChangeException> {

    private final static String ERROR_CODE = "INVALID_WEIGHT_CHANGE";
    private final static String ERROR_MESSAGE = "The specified weight loss must not make the dinosaur weight less " +
            "than 100 kg.";

    @Override
    public Response toResponse(InvalidWeightChangeException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
