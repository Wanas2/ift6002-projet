package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

public class ModifyWeightAction implements ExecutableAction {

    private final int weightVariation;
    private final Dinosaur dinosaur;

    public ModifyWeightAction(int weightVariation, Dinosaur dinosaur) {
        this.weightVariation = weightVariation;
        this.dinosaur = dinosaur;
    }

    @Override
    public void execute() {
        dinosaur.modifyWeight(weightVariation);
    }
}
