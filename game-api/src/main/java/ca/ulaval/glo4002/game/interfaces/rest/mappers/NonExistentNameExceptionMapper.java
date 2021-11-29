package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.herd.NonExistentNameException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NonExistentNameExceptionMapper extends GeneralNotFoundExceptionMapper
        implements ExceptionMapper<NonExistentNameException> {

    private final static String ERROR_CODE = "NON_EXISTENT_NAME";
    private final static String ERROR_MESSAGE = "The specified name does not exist.";

    @Override
    public Response toResponse(NonExistentNameException exception) {
        return notFoundResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
