package ca.ulaval.glo4002.game.interfaces.rest.game;

import ca.ulaval.glo4002.game.applicationService.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameResourceTest {

    private final static int STATUS_200_OK = 200;
    private final static int A_TURN_NUMBER = 143;

    private GameService gameService;
    private GameResource gameResource;

    @BeforeEach
    public void setUp() {
        gameService = mock(GameService.class);
        TurnAssembler turnAssembler = new TurnAssembler();
        gameResource = new GameResource(gameService, turnAssembler);
    }

    @Test
    public void whenPlayTurn_thenTurnShouldBePlayed() {
        gameResource.playTurn();

        verify(gameService).playTurn();
    }

    @Test
    public void givenATurnNumber_whenPlayTurn_thenResponseEntityShouldContainTheTurnNumber() {
        when(gameService.playTurn()).thenReturn(A_TURN_NUMBER);

        Response response = gameResource.playTurn();

        TurnNumberDTO responseEntity = (TurnNumberDTO)response.getEntity();
        assertEquals(A_TURN_NUMBER, responseEntity.turnNumber);
    }

    @Test
    public void whenPlayTurn_thenResponseStatusShouldBe200() {
        Response response = gameResource.playTurn();

        assertEquals(STATUS_200_OK, response.getStatus());
    }

    @Test
    public void whenReset_thenShouldCallGameService() {
        gameResource.reset();

        verify(gameService).reset();
    }

    @Test
    public void whenReset_thenResponseStatusShouldBe200() {
        Response response = gameResource.reset();

        assertEquals(STATUS_200_OK, response.getStatus());
    }
}
