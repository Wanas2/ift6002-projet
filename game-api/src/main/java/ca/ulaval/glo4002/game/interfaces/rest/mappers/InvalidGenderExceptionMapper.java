package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidGenderException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidGenderExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<InvalidGenderException> {

    private final static String ERROR_CODE = "INVALID_GENDER";
    private final static String ERROR_MESSAGE = "The specified gender must be \"m\" or \"f\".";

    @Override
    public Response toResponse(InvalidGenderException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
