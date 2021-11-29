package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.AdultDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;

public class AddAdultDinosaurAction implements ExecutableAction {

    private final Herd herd;
    private final AdultDinosaur adultDinosaur;

    public AddAdultDinosaurAction(Herd herd, AdultDinosaur adultDinosaur) {
        this.herd = herd;
        this.adultDinosaur = adultDinosaur;
    }

    @Override
    public void execute() {
        herd.addAdultDinosaur(adultDinosaur);
    }
}
