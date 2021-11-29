package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import javax.ws.rs.core.Response;

public class GeneralNotFoundExceptionMapper {

    public Response notFoundResponse(String error, String message) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(error, message);

        return Response.status(404).entity(exceptionDTO).build();
    }
}
