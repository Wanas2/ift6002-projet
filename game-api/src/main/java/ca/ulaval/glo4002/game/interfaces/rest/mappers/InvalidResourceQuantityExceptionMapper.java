package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.interfaces.rest.food.InvalidResourceQuantityException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidResourceQuantityExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<InvalidResourceQuantityException> {

    private final static String ERROR_CODE = "INVALID_RESOURCE_QUANTITY";
    private final static String ERROR_MESSAGE = "Resource quantities must be positive.";

    @Override
    public Response toResponse(InvalidResourceQuantityException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
