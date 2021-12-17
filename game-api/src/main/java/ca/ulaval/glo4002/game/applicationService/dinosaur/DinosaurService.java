package ca.ulaval.glo4002.game.applicationService.dinosaur;

import ca.ulaval.glo4002.game.domain.Game;
import ca.ulaval.glo4002.game.domain.dinosaur.*;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;

import java.util.List;
import java.util.Optional;

public class DinosaurService {

    private final DinosaurFactory dinosaurFactory;
    private final Game game;
    private final BabyFetcher babyFetcher;

    private Herd herd;

    public DinosaurService(DinosaurFactory dinosaurFactory, Game game, BabyFetcher babyFetcher) {
        this.dinosaurFactory = dinosaurFactory;
        this.game = game;
        this.babyFetcher = babyFetcher;
    }

    public void addAdultDinosaur(String name, int weight, String gender, String species) {
        this.herd = game.getHerd();
        if(herd.hasDinosaurWithName(name)) {
            throw new DuplicateNameException();
        }
        Dinosaur adultDinosaur = dinosaurFactory.createAdultDinosaur(gender, weight, species, name);
        game.addAdultDinosaur(adultDinosaur);
    }

    public void breedDinosaur(String babyDinosaurName, String fatherName, String motherName) {
        this.herd = game.getHerd();

        Dinosaur fatherDinosaur = herd.getDinosaurWithName(fatherName);
        Dinosaur motherDinosaur = herd.getDinosaurWithName(motherName);

        Optional<Dinosaur> babyDinosaur = babyFetcher.fetch(fatherDinosaur, motherDinosaur, babyDinosaurName);
        babyDinosaur.ifPresent(game::addBabyDinosaur);
    }

    public String prepareSumoFight(String dinosaurChallengerName, String dinosaurChallengeeName) {
        this.herd = game.getHerd();

        Dinosaur dinosaurChallenger = herd.getDinosaurWithName(dinosaurChallengerName);
        Dinosaur dinosaurChallengee = herd.getDinosaurWithName(dinosaurChallengeeName);

        String predictedWinner = herd.predictWinnerSumoFight(dinosaurChallenger, dinosaurChallengee);
        game.addSumoFight(dinosaurChallenger, dinosaurChallengee);
        return predictedWinner;
    }

    public void updateDinosaurWeight(String dinosaurName, int weight) {
        this.herd = game.getHerd();

        Dinosaur dinosaur = herd.getDinosaurWithName(dinosaurName);
        dinosaur.validateWeightVariation(weight);
        game.modifyDinosaurWeight(weight, dinosaur);
    }

    public Dinosaur showDinosaur(String dinosaurName) {
        this.herd = game.getHerd();
        return herd.getDinosaurWithName(dinosaurName);
    }

    public List<Dinosaur> showAllDinosaurs() {
        this.herd = game.getHerd();
        return herd.getAllDinosaurs();
    }
}
