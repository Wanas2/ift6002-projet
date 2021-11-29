package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidBabyWeightChangeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidBabyWeightChangeExceptionMapper extends GeneralBadRequestExceptionMapper
        implements ExceptionMapper<InvalidBabyWeightChangeException> {

    private final static String ERROR_CODE = "INVALID_BABY_WEIGHT_CHANGE";
    private final static String ERROR_MESSAGE = "The weight of a baby dinosaur can not be changed.";

    @Override
    public Response toResponse(InvalidBabyWeightChangeException exception) {
        return badRequestResponse(ERROR_CODE, ERROR_MESSAGE);
    }
}
