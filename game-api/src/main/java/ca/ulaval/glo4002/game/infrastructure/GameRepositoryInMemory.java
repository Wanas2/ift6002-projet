package ca.ulaval.glo4002.game.infrastructure;

import ca.ulaval.glo4002.game.domain.Game;
import ca.ulaval.glo4002.game.domain.GameRepository;

import java.util.Optional;

public class GameRepositoryInMemory implements GameRepository {

    private Game game;

    @Override
    public void save(Game game) {
        this.game = game;
    }

    @Override
    public Optional<Game> find() {
        return Optional.ofNullable(this.game);
    }

    @Override
    public void delete() {
        this.game = null;
    }
}
