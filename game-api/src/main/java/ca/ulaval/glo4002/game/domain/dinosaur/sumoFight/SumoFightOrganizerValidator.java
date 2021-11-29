package ca.ulaval.glo4002.game.domain.dinosaur.sumoFight;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Species;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.exceptions.ArmsTooShortException;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.exceptions.MaxCombatsReachedException;

import java.util.List;

public class SumoFightOrganizerValidator {

    public void validateSumoFighters(List<Dinosaur> dinosaursAlreadyFighting, Dinosaur dinosaurChallenger,
                                     Dinosaur dinosaurChallengee) {
        if(dinosaursAlreadyFighting.contains(dinosaurChallenger)
                || dinosaursAlreadyFighting.contains(dinosaurChallengee)) {
            throw new DinosaurAlreadyParticipatingException();
        }
        if(dinosaurChallenger.getSpecies() == Species.TyrannosaurusRex
                || dinosaurChallengee.getSpecies() == Species.TyrannosaurusRex) {
            throw new ArmsTooShortException();
        }
    }

    public void validateSumoFight(int numberOfFightDone, int maxFightToDo) {
        if(numberOfFightDone >= maxFightToDo) {
            throw new MaxCombatsReachedException();
        }
    }
}
