package ca.ulaval.glo4002.game.infrastructure;

import ca.ulaval.glo4002.game.domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class GameRepositoryInMemoryTest {

    private Game aGame;
    private GameRepositoryInMemory gameRepository;

    @BeforeEach
    public void setUp() {
        aGame = mock(Game.class);
        gameRepository = new GameRepositoryInMemory();
    }

    @Test
    public void shouldBeInitiallyEmpty() {
        assertTrue(gameRepository.find().isEmpty());
    }

    @Test
    public void whenSave_thenShouldNotBeEmpty() {
        gameRepository.save(aGame);

        assertFalse(gameRepository.find().isEmpty());
    }

    @Test
    public void givenASavedGame_whenFind_thenTheGameShouldBeReturned() {
        gameRepository.save(aGame);

        Optional<Game> gameReturned = gameRepository.find();

        assertEquals(aGame, gameReturned.get());
    }

    @Test
    public void givenASavedGame_whenDelete_thenThenShouldBeEmpty() {
        gameRepository.save(aGame);

        gameRepository.delete();

        assertTrue(gameRepository.find().isEmpty());
    }
}
