package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.Optional;

public interface BabyFetcher {

    Optional<BabyDinosaur> fetch(Dinosaur fatherDinosaur, Dinosaur motherDinosaur, String babyDinoName);
}
