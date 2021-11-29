package ca.ulaval.glo4002.game.domain;

import java.util.Optional;

public interface GameRepository {

    void save(Game game);

    Optional<Game> find();

    void delete();
}
