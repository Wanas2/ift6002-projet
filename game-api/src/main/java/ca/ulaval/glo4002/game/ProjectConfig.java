package ca.ulaval.glo4002.game;

import ca.ulaval.glo4002.game.applicationService.GameService;
import ca.ulaval.glo4002.game.applicationService.dinosaur.DinosaurService;
import ca.ulaval.glo4002.game.applicationService.food.ResourceService;
import ca.ulaval.glo4002.game.domain.Game;
import ca.ulaval.glo4002.game.domain.GameRepository;
import ca.ulaval.glo4002.game.domain.Turn;
import ca.ulaval.glo4002.game.domain.dinosaur.BabyFetcher;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.CarnivorousDinosaurFeeder;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.HerbivorousDinosaurFeeder;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.WeakerToStrongerEatingOrder;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.SumoFightOrganizer;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.SumoFightOrganizerValidator;
import ca.ulaval.glo4002.game.domain.food.CookItSubscription;
import ca.ulaval.glo4002.game.domain.food.FoodHistory;
import ca.ulaval.glo4002.game.domain.food.FoodProvider;
import ca.ulaval.glo4002.game.domain.food.Pantry;
import ca.ulaval.glo4002.game.domain.food.foodDistribution.FoodDistributor;
import ca.ulaval.glo4002.game.domain.food.foodDistribution.WaterSplitter;
import ca.ulaval.glo4002.game.infrastructure.GameRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.BabyFetcherFromExternalAPI;
import ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.DinosaurBreederExternal;
import ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.ParentsGenderValidator;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.DinosaurResource;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.assembler.DinosaurAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.assembler.SumoAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.food.FoodResource;
import ca.ulaval.glo4002.game.interfaces.rest.food.FoodValidator;
import ca.ulaval.glo4002.game.interfaces.rest.food.assembler.FoodAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.food.assembler.FoodSummaryAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.game.GameResource;
import ca.ulaval.glo4002.game.interfaces.rest.game.TurnAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.mappers.*;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.ArrayList;
import java.util.List;

public class ProjectConfig extends ResourceConfig {

    public ProjectConfig() {
        registerResources();
        registerExceptionMappers();
    }

    private void registerResources() {
        GameRepository gameRepository = new GameRepositoryInMemory();

        Game game = gameRepository.find().orElse(provideNewGame());

        Pantry pantry = game.getPantry();
        Herd herd = game.getHerd();

        FoodValidator foodValidator = new FoodValidator();
        DinosaurFactory dinosaurFactory = new DinosaurFactory(pantry, pantry);

        DinosaurBreederExternal dinoBreeder = new DinosaurBreederExternal();
        ParentsGenderValidator parentsGenderValidator = new ParentsGenderValidator();
        BabyFetcher dinosaurBabyFetcher
                = new BabyFetcherFromExternalAPI(dinoBreeder, dinosaurFactory, parentsGenderValidator);

        TurnAssembler turnAssembler = new TurnAssembler();
        FoodAssembler foodAssembler = new FoodAssembler();
        DinosaurAssembler dinosaurAssembler = new DinosaurAssembler();
        SumoAssembler sumoAssembler = new SumoAssembler();
        FoodSummaryAssembler foodSummaryAssembler = new FoodSummaryAssembler(foodAssembler);

        ResourceService resourceService = new ResourceService(pantry, game);
        DinosaurService dinosaurService = new DinosaurService(dinosaurFactory, herd, game, dinosaurBabyFetcher);
        GameService gameService = new GameService(game, gameRepository);

        GameResource gameResource = new GameResource(gameService, turnAssembler);
        FoodResource foodResource = new FoodResource(resourceService, foodValidator, foodAssembler,
                foodSummaryAssembler);
        DinosaurResource dinosaurResource = new DinosaurResource(dinosaurService, dinosaurAssembler,
                sumoAssembler);

        register(gameResource);
        register(foodResource);
        register(dinosaurResource);
    }

    private void registerExceptionMappers() {
        register(new DuplicateNameExceptionMapper());
        register(new InvalidGenderExceptionMapper());
        register(new InvalidSpeciesExceptionMapper());
        register(new InvalidWeightExceptionMapper());
        register(new NonExistentNameExceptionMapper());
        register(new InvalidResourceQuantityExceptionMapper());
        register(new InvalidFatherExceptionMapper());
        register(new InvalidMotherExceptionMapper());
        register(new MaxCombatsReachedExceptionMapper());
        register(new ArmsTooShortExceptionMapper());
        register(new DinosaurAlreadyParticipatingExceptionMapper());
        register(new InvalidWeightChangeExceptionMapper());
        register(new InvalidBabyWeightChangeExceptionMapper());
    }

    private Game provideNewGame() {
        FoodProvider foodProvider = new CookItSubscription();
        FoodDistributor foodDistributor = new FoodDistributor();
        WaterSplitter waterSplitter = new WaterSplitter();
        FoodHistory foodHistory = new FoodHistory();
        Pantry pantry = new Pantry(foodProvider, foodDistributor, waterSplitter, foodHistory);

        WeakerToStrongerEatingOrder eatingOrder = new WeakerToStrongerEatingOrder();
        CarnivorousDinosaurFeeder carnivorousDinosaurFeeder = new CarnivorousDinosaurFeeder(eatingOrder);
        HerbivorousDinosaurFeeder herbivorousDinosaurFeeder = new HerbivorousDinosaurFeeder(eatingOrder);
        List<Dinosaur> dinosaurs = new ArrayList<>();
        SumoFightOrganizerValidator sumoFightOrganizerValidator = new SumoFightOrganizerValidator();
        SumoFightOrganizer sumoFightOrganizer = new SumoFightOrganizer(sumoFightOrganizerValidator);
        Herd herd = new Herd(dinosaurs, sumoFightOrganizer,
                List.of(carnivorousDinosaurFeeder, herbivorousDinosaurFeeder));

        Turn turn = new Turn();
        return new Game(herd, pantry, turn);
    }
}
