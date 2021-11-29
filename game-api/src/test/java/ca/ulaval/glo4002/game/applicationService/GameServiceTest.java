package ca.ulaval.glo4002.game.applicationService;

import ca.ulaval.glo4002.game.domain.Game;
import ca.ulaval.glo4002.game.domain.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GameServiceTest {

    private Game game;
    private GameRepository gameRepository;
    private GameService gameService;

    @BeforeEach
    void setUp() {
        game = mock(Game.class);
        gameRepository = mock(GameRepository.class);
        gameService = new GameService(game, gameRepository);
    }

    @Test
    public void whenPlayTurn_thenGameIsPlayed() {
        gameService.playTurn();

        verify(game).playTurn();
    }

    @Test
    public void whenPlayTurn_thenGameIsSaved() {
        gameService.playTurn();

        verify(gameRepository).save(game);
    }

    @Test
    public void whenReset_thenGameIsReset() {
        gameService.reset();

        verify(game).reset();
    }

    @Test
    public void whenReset_thenTheSavedGameIsDeleted() {
        gameService.reset();

        verify(gameRepository).delete();
    }
}
