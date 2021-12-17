package ca.ulaval.glo4002.game.domain.dinosaur.herd;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodNeed;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.SumoFightOrganizer;

import java.util.*;

public class Herd {

    private final List<Dinosaur> dinosaurs;
    private final SumoFightOrganizer sumoFightOrganizer;
    private final List<DinosaurFeeder> dinosaurFeeders;

    public Herd(List<Dinosaur> dinosaurs, SumoFightOrganizer sumoFightOrganizer,
                List<DinosaurFeeder> dinosaurFeeders) {
        this.dinosaurs = dinosaurs;
        this.sumoFightOrganizer = sumoFightOrganizer;
        this.dinosaurFeeders = dinosaurFeeders;
    }

    public boolean hasDinosaurWithName(String name) {
        for(Dinosaur dino: dinosaurs) {
            if(dino.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addDinosaur(Dinosaur dinosaur) {
        if(!hasDinosaurWithName(dinosaur.getName())) {
            dinosaurs.add(dinosaur);
        }
    }

    public void feedDinosaurs() {
        Map<Dinosaur, List<FoodNeed>> dinosaursWithNeed = new HashMap<>();
        dinosaurs.forEach(dinosaur->dinosaursWithNeed.put(dinosaur, dinosaur.askForFood()));

        dinosaurFeeders.forEach(dinosaurFeeder->dinosaurFeeder.feedDinosaurs(dinosaursWithNeed));

        removeFastingDinosaurs();
    }

    public void reset() {
        dinosaurs.clear();
    }

    public Dinosaur getDinosaurWithName(String dinosaurName) {
        for(Dinosaur dino: dinosaurs) {
            if(dino.getName().equals(dinosaurName)) {
                return dino;
            }
        }
        throw new NonExistentNameException();
    }

    public List<Dinosaur> getAllDinosaurs() {
        return dinosaurs;
    }

    private void removeFastingDinosaurs() {
        dinosaurs.removeIf((dinosaur)->!dinosaur.isAlive());
    }

    public void sumoFight(Dinosaur dinosaurChallenger, Dinosaur dinosaurChallengee) {
        List<Dinosaur> dinosaursWinners = sumoFightOrganizer.sumoFight(dinosaurChallenger,
                dinosaurChallengee);

        List<Dinosaur> dinosaursLosers = new ArrayList<>();
        Collections.addAll(dinosaursLosers, dinosaurChallenger, dinosaurChallengee);
        dinosaursLosers.removeAll(dinosaursWinners);

        dinosaursWinners.forEach(Dinosaur::winFight);
        dinosaursLosers.forEach(Dinosaur::loseFight);
    }

    public String predictWinnerSumoFight(Dinosaur dinosaurChallenger, Dinosaur dinosaurChallengee) {
        return sumoFightOrganizer.scheduleSumoFight(dinosaurChallenger, dinosaurChallengee);
    }

    public void resetSumoFight() {
        sumoFightOrganizer.reset();
    }

    public void increasingBabiesWeight() {
        dinosaurs.forEach(Dinosaur::increaseWeight);
    }
}