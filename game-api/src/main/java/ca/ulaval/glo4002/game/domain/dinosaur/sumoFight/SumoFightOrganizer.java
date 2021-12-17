package ca.ulaval.glo4002.game.domain.dinosaur.sumoFight;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

import java.util.ArrayList;
import java.util.Arrays;
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
        return planFight(dinosaurChallenger, dinosaurChallengee);
    }

    public String scheduleSumoFight(Dinosaur dinosaurChallenger, Dinosaur dinosaurChallengee) {
        sumoFightOrganizerValidator.validateSumoFight(numberOfFightDone, MAX_NUMBER_OF_FIGHTS_PER_TURN);
        sumoFightOrganizerValidator.validateSumoFighters(dinosaursAlreadyFought,
                dinosaurChallenger, dinosaurChallengee);

        numberOfFightDone++;
        dinosaursAlreadyFought.addAll(Arrays.asList(dinosaurChallenger, dinosaurChallengee));

        List<Dinosaur> plannedResult = planFight(dinosaurChallenger, dinosaurChallengee);
        return plannedResult.size() > 1 ? TIE_FIGHT : plannedResult.get(0).getName();
    }

    public void reset() {
        numberOfFightDone = 0;
        dinosaursAlreadyFought.clear();
    }

    private List<Dinosaur> planFight(Dinosaur dinosaurChallenger, Dinosaur dinosaurChallengee) {
        int strengthDifference = dinosaurChallenger.compareStrength(dinosaurChallengee);

        if(strengthDifference > 0) {
            return List.of(dinosaurChallenger);
        }

        if(strengthDifference < 0) {
            return List.of(dinosaurChallengee);
        }

        return Arrays.asList(dinosaurChallenger, dinosaurChallengee);
    }
}
