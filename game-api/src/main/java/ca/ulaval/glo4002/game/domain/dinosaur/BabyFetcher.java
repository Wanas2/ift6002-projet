package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.Optional;

public interface BabyFetcher {

    Optional<Dinosaur> fetch(Dinosaur fatherDinosaur, Dinosaur motherDinosaur, String babyDinoName);
}
