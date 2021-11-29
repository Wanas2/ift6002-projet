package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidMotherException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidMotherExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<InvalidMotherException> {

    private final static String ERROR_CODE = "INVALID_MOTHER";
    private final static String ERROR_MESSAGE = "Mother must be a female.";

    @Override
    public Response toResponse(InvalidMotherException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
