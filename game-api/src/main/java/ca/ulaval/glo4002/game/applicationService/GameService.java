package ca.ulaval.glo4002.game.applicationService;

import ca.ulaval.glo4002.game.domain.Game;
import ca.ulaval.glo4002.game.domain.GameRepository;

public class GameService {

    private final Game game;
    private final GameRepository gameRepository;

    public GameService(Game game, GameRepository gameRepository) {
        this.game = game;
        this.gameRepository = gameRepository;
    }

    public int playTurn() {
        int turnNumber = game.playTurn();
        gameRepository.save(game);
        return turnNumber;
    }

    public void reset() {
        game.reset();
        gameRepository.delete();
    }
}
