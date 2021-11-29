package ca.ulaval.glo4002.game.interfaces.rest.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralNotFoundExceptionMapperTest {

    private final static int STATUS_404_NOT_FOUND = 404;
    private final static String AN_ERROR_CODE = "ERROR";
    private final static String A_MESSAGE = "Erreur";

    private GeneralNotFoundExceptionMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new GeneralNotFoundExceptionMapper();
    }

    @Test
    public void whenNotFoundResponse_thenResponseStatusShouldBe404() {
        Response response = mapper.notFoundResponse(AN_ERROR_CODE, A_MESSAGE);

        assertEquals(STATUS_404_NOT_FOUND, response.getStatus());
    }

    @Test
    public void whenNotFoundResponse_thenResponseShouldContainErrorCodeAndMessage() {
        Response response = mapper.notFoundResponse(AN_ERROR_CODE, A_MESSAGE);

        ExceptionDTO responseBody = (ExceptionDTO)response.getEntity();
        assertEquals(AN_ERROR_CODE, responseBody.error);
        assertEquals(A_MESSAGE, responseBody.description);
    }
}
