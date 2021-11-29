package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.applicationService.dinosaur.DuplicateNameException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DuplicateNameExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<DuplicateNameException> {

    private final static String ERROR_CODE = "DUPLICATE_NAME";
    private final static String ERROR_MESSAGE = "The specified name already exists and must be unique.";

    @Override
    public Response toResponse(DuplicateNameException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
