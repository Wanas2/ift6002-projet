package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidFatherException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidFatherExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<InvalidFatherException> {

    private final static String ERROR_CODE = "INVALID_FATHER";
    private final static String ERROR_MESSAGE = "Father must be a male.";

    @Override
    public Response toResponse(InvalidFatherException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
