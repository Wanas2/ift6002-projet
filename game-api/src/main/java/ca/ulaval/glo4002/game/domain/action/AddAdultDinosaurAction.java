package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;

public class AddAdultDinosaurAction implements ExecutableAction {

    private final Herd herd;
    private final Dinosaur dinosaur;

    public AddAdultDinosaurAction(Herd herd, Dinosaur dinosaur) {
        this.herd = herd;
        this.dinosaur = dinosaur;
    }

    @Override
    public void execute() {
        herd.addDinosaur(dinosaur);
    }
}
