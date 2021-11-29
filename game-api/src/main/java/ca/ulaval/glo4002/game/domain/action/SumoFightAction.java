package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;

public class SumoFightAction implements ExecutableAction {

    private final Herd herd;
    private final Dinosaur dinosaurChallenger;
    private final Dinosaur dinosaurChallengee;

    public SumoFightAction(Herd herd, Dinosaur dinosaurChallenger, Dinosaur dinosaurChallengee) {
        this.herd = herd;
        this.dinosaurChallenger = dinosaurChallenger;
        this.dinosaurChallengee = dinosaurChallengee;
    }

    @Override
    public void execute() {
        herd.organizeSumoFight(dinosaurChallenger, dinosaurChallengee);
    }
}
