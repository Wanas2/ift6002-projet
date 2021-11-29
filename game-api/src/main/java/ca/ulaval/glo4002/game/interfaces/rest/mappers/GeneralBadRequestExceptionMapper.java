package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import javax.ws.rs.core.Response;

public class GeneralBadRequestExceptionMapper {

    public Response badRequestResponse(String error, String message) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(error, message);

        return Response.status(400).entity(exceptionDTO).build();
    }
}
