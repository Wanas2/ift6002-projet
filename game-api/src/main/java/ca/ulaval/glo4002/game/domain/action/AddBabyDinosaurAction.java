package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;

public class AddBabyDinosaurAction implements ExecutableAction {

    private final Herd herd;
    private final Dinosaur babyDinosaur;

    public AddBabyDinosaurAction(Herd herd, Dinosaur babyDinosaur) {
        this.herd = herd;
        this.babyDinosaur = babyDinosaur;
    }

    @Override
    public void execute() {
        herd.addDinosaur(babyDinosaur);
    }
}
