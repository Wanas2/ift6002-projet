package ca.ulaval.glo4002.game.domain.dinosaur.herd;

import ca.ulaval.glo4002.game.domain.dinosaur.AdultDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.BabyDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodNeed;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.SumoFightOrganizer;

import java.util.*;

public class Herd {

    private final List<BabyDinosaur> babiesDinosaurs = new ArrayList<>();
    private final List<AdultDinosaur> adultDinosaurs = new ArrayList<>();
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

    public void addAdultDinosaur(AdultDinosaur adultDinosaur) {
        if(!hasDinosaurWithName(adultDinosaur.getName())) {
            dinosaurs.add(adultDinosaur);
            adultDinosaurs.add(adultDinosaur);
        }
    }

    public void addBabyDinosaur(BabyDinosaur babyDinosaur) {
        if(!hasDinosaurWithName(babyDinosaur.getName())) {
            dinosaurs.add(babyDinosaur);
            babiesDinosaurs.add(babyDinosaur);
        }
    }

    public void feedDinosaurs() {
        Map<Dinosaur, List<FoodNeed>> dinosaursWithNeed = new HashMap<>();
        dinosaurs.forEach(dinosaur->dinosaursWithNeed.put(dinosaur, dinosaur.askForFood()));

        dinosaurFeeders.forEach(dinosaurFeeder->dinosaurFeeder.feedDinosaurs(dinosaursWithNeed));

        removeFastingDinosaurs();
        updateAdultDinosaursList();
        updateBabyDinosaursList();
    }

    public void reset() {
        dinosaurs.clear();
        babiesDinosaurs.clear();
        adultDinosaurs.clear();
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

    public void organizeSumoFight(Dinosaur dinosaurChallenger, Dinosaur dinosaurChallengee) {
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

    // A utiliser dans game quand le service sera fait
    public void modifyDinosaurWeight(String dinosaurName, int weightValue) {
        Dinosaur dinosaur = getDinosaurWithName(dinosaurName);
        dinosaur.modifyWeight(weightValue);
    }

    public void increasingBabiesWeight() {
        updateBabyDinosaursList();
        for(BabyDinosaur babyDinosaur: babiesDinosaurs) {
            babyDinosaur.increaseWeight();
        }

        growBabyToAdultDinosaur();
    }

    private void updateAdultDinosaursList() {
        List<AdultDinosaur> dinosaursToRemove = new ArrayList<>();
        for(AdultDinosaur adultDinosaur: adultDinosaurs) {
            if(!hasDinosaurWithName(adultDinosaur.getName())) {
                dinosaursToRemove.add(adultDinosaur);
            }
        }
        adultDinosaurs.removeAll(dinosaursToRemove);
    }

    private void updateBabyDinosaursList() {
        List<BabyDinosaur> babiesToRemove = new ArrayList<>();
        for(BabyDinosaur babyDinosaur: babiesDinosaurs) {
            if(!hasDinosaurWithName(babyDinosaur.getName())) {
                babiesToRemove.add(babyDinosaur);
            }
        }
        babiesDinosaurs.removeAll(babiesToRemove);
    }

    private void growBabyToAdultDinosaur() {
        List<AdultDinosaur> babyToAdultDinosaur = new ArrayList<>();
        List<BabyDinosaur> babiesToRemove = new ArrayList<>();
        for(BabyDinosaur babyDinosaur: babiesDinosaurs) {
            Optional<AdultDinosaur> adultDinosaur = babyDinosaur.becomeAdult();
            if(adultDinosaur.isPresent()) {
                babyToAdultDinosaur.add(adultDinosaur.get());
                babiesToRemove.add(babyDinosaur);
            }
        }

        babiesDinosaurs.removeAll(babiesToRemove);
        dinosaurs.removeAll(babiesToRemove);
        dinosaurs.addAll(babyToAdultDinosaur);
    }
}