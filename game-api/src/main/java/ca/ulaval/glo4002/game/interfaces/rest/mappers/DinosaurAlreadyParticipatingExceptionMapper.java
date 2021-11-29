package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.exceptions.DinosaurAlreadyParticipatingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DinosaurAlreadyParticipatingExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<DinosaurAlreadyParticipatingException> {

    private final static String ERROR_CODE = "DINOSAUR_ALREADY_PARTICIPATING";
    private final static String ERROR_MESSAGE = "Dinosaur is already participating.";

    @Override
    public Response toResponse(DinosaurAlreadyParticipatingException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
