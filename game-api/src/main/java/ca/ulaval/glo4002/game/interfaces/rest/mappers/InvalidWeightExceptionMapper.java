package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidWeightException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidWeightExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<InvalidWeightException> {

    private final static String ERROR_CODE = "INVALID_WEIGHT";
    private final static String ERROR_MESSAGE = "The specified weight must be equal or greater than 100 kg.";

    @Override
    public Response toResponse(InvalidWeightException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
