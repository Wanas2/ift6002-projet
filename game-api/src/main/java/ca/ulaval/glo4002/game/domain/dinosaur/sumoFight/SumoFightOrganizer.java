package ca.ulaval.glo4002.game.domain.dinosaur.sumoFight;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SumoFightOrganizer {

    private final static String TIE_FIGHT = "tie";
    private final static int MAX_NUMBER_OF_FIGHTS_PER_TURN = 2;

    private int numberOfFightDone = 0;
    private final List<Dinosaur> dinosaursAlreadyFought = new ArrayList<>();
    private final SumoFightOrganizerValidator sumoFightOrganizerValidator;

    public SumoFightOrganizer(SumoFightOrganizerValidator sumoFightOrganizerValidator) {
        this.sumoFightOrganizerValidator = sumoFightOrganizerValidator;
    }

    public List<Dinosaur> sumoFight(Dinosaur dinosaurChallenger, Dinosaur dinosaurChallengee) {
        int strengthDifference = dinosaurChallenger.compareStrength(dinosaurChallengee);
        if(strengthDifference > 0) {
            return new ArrayList<>(Collections.singleton(dinosaurChallenger));
        } else if(strengthDifference < 0) {
            return new ArrayList<>(Collections.singleton(dinosaurChallengee));
        }

        return new ArrayList<>(Arrays.asList(dinosaurChallenger, dinosaurChallengee));
    }

    public String scheduleSumoFight(Dinosaur dinosaurChallenger, Dinosaur dinosaurChallengeee) {
        sumoFightOrganizerValidator.validateSumoFight(numberOfFightDone, MAX_NUMBER_OF_FIGHTS_PER_TURN);
        sumoFightOrganizerValidator.validateSumoFighters(dinosaursAlreadyFought,
                dinosaurChallenger, dinosaurChallengeee);

        numberOfFightDone++;
        dinosaursAlreadyFought.addAll(Arrays.asList(dinosaurChallenger, dinosaurChallengeee));
        int strengthDifference = dinosaurChallenger.compareStrength(dinosaurChallengeee);
        if(strengthDifference > 0) {
            return dinosaurChallenger.getName();
        } else if(strengthDifference < 0) {
            return dinosaurChallengeee.getName();
        }

        return TIE_FIGHT;
    }

    public void reset() {
        numberOfFightDone = 0;
        dinosaursAlreadyFought.clear();
    }
}
