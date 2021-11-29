package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.exceptions.MaxCombatsReachedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MaxCombatsReachedExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<MaxCombatsReachedException> {

    private final static String ERROR_CODE = "MAX_COMBATS_REACHED";
    private final static String ERROR_MESSAGE = "Max number of combats has been reached.";

    @Override
    public Response toResponse(MaxCombatsReachedException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
