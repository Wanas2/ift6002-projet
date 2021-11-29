package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidSpeciesException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidSpeciesExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<InvalidSpeciesException> {

    private final static String ERROR_CODE = "INVALID_SPECIES";
    private final static String ERROR_MESSAGE = "The specified species is not supported.";

    @Override
    public Response toResponse(InvalidSpeciesException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
