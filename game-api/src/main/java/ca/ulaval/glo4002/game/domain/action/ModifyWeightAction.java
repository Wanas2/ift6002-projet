package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.AdultDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

public class ModifyWeightAction implements ExecutableAction {

    private final int weightVariation;
    private final AdultDinosaur dinosaur;

    public ModifyWeightAction(int weightVariation, Dinosaur dinosaur) {
        this.weightVariation = weightVariation;
        this.dinosaur = (AdultDinosaur) dinosaur;
    }

    @Override
    public void execute() {
        dinosaur.modifyWeight(weightVariation);
    }
}
