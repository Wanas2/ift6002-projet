package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralBadRequestExceptionMapperTest {

    private final static int STATUS_400_BAD_REQUEST = 400;
    private final static String AN_ERROR_CODE = "ERROR";
    private final static String A_MESSAGE = "Erreur";

    private GeneralBadRequestExceptionMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new GeneralBadRequestExceptionMapper();
    }

    @Test
    public void whenBadRequestResponse_thenResponseStatusShouldBe400() {
        Response response = mapper.badRequestResponse(AN_ERROR_CODE, A_MESSAGE);

        assertEquals(STATUS_400_BAD_REQUEST, response.getStatus());
    }

    @Test
    public void whenBadRequestResponse_thenResponseShouldContainErrorCodeAndMessage() {
        Response response = mapper.badRequestResponse(AN_ERROR_CODE, A_MESSAGE);

        ExceptionDTO responseBody = (ExceptionDTO)response.getEntity();
        assertEquals(AN_ERROR_CODE, responseBody.error);
        assertEquals(A_MESSAGE, responseBody.description);
    }
}
