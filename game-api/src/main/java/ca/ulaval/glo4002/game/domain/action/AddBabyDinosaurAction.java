package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.BabyDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;

public class AddBabyDinosaurAction implements ExecutableAction {

    private final Herd herd;
    private final BabyDinosaur babyDinosaur;

    public AddBabyDinosaurAction(Herd herd, BabyDinosaur babyDinosaur) {
        this.herd = herd;
        this.babyDinosaur = babyDinosaur;
    }

    @Override
    public void execute() {
        herd.addBabyDinosaur(babyDinosaur);
    }
}
