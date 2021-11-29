package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.exceptions.ArmsTooShortException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ArmsTooShortExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<ArmsTooShortException> {

    private final static String ERROR_CODE = "ARMS_TOO_SHORT";
    private final static String ERROR_MESSAGE = "Tyrannosaurus Rex can't participate.";

    @Override
    public Response toResponse(ArmsTooShortException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
