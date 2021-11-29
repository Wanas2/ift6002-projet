package ca.ulaval.glo4002.game.interfaces.rest.dinosaur;

import ca.ulaval.glo4002.game.applicationService.dinosaur.DinosaurService;
import ca.ulaval.glo4002.game.domain.dinosaur.AdultDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Gender;
import ca.ulaval.glo4002.game.domain.dinosaur.Species;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumptionStrategy;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.assembler.DinosaurAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.assembler.SumoAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto.BreedingRequestDTO;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto.DinosaurDTO;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto.SumoRequestDTO;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto.SumoResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DinosaurResourceTest {

    private final static int STATUS_200_OK = 200;
    private final static String A_BABY_DINOSAUR_NAME = "Junior";
    private final static String A_DINOSAUR_NAME = "Bobi";
    private final static String ANOTHER_DINOSAUR_NAME = "Bob";
    private final static int A_WEIGHT = 235;
    private final static String GENDER = "f";
    private final static String SPECIES = "Ankylosaurus";
    private FoodConsumptionStrategy consumptionStrategy;

    private BreedingRequestDTO aBreedingRequestDTO;
    private DinosaurDTO aDinosaurDTO;
    private GrowDTO aGrowDTO;
    private SumoRequestDTO aSumoRequestDTO;
    private Dinosaur aDinosaur;
    private Dinosaur anotherDinosaur;
    private List<Dinosaur> dinosaurs;
    private DinosaurService dinosaurService;
    private DinosaurResource dinosaurResource;

    @BeforeEach
    public void setup() {
        aDinosaurDTO = new DinosaurDTO(A_DINOSAUR_NAME, A_WEIGHT, GENDER, SPECIES);
        aBreedingRequestDTO = new BreedingRequestDTO(A_BABY_DINOSAUR_NAME, A_DINOSAUR_NAME, ANOTHER_DINOSAUR_NAME);
        aSumoRequestDTO = new SumoRequestDTO(A_DINOSAUR_NAME, ANOTHER_DINOSAUR_NAME);
        aGrowDTO = new GrowDTO(A_WEIGHT);
        consumptionStrategy = mock(FoodConsumptionStrategy.class);
        aDinosaur = new AdultDinosaur(Species.Ankylosaurus, A_WEIGHT, A_DINOSAUR_NAME, Gender.F, consumptionStrategy);
        anotherDinosaur =
                new AdultDinosaur(Species.Ankylosaurus, A_WEIGHT, ANOTHER_DINOSAUR_NAME, Gender.F, consumptionStrategy);
        aSumoRequestDTO = new SumoRequestDTO(A_DINOSAUR_NAME, ANOTHER_DINOSAUR_NAME);
        dinosaurs = new ArrayList<>();
        dinosaurService = mock(DinosaurService.class);
        DinosaurAssembler dinosaurAssembler = new DinosaurAssembler();
        SumoAssembler sumoAssembler = new SumoAssembler();
        dinosaurResource = new DinosaurResource(dinosaurService, dinosaurAssembler, sumoAssembler);
    }

    @Test
    public void givenADinosaurDTOWithValidData_whenAddDinosaur_thenShouldAddTheDinosaur() {
        dinosaurResource.addAdultDinosaur(aDinosaurDTO);

        verify(dinosaurService).
                addAdultDinosaur(aDinosaurDTO.name, aDinosaurDTO.weight, aDinosaurDTO.gender, aDinosaurDTO.species);
    }

    @Test
    public void givenADinosaurDTOWithValidData_whenAddDinosaur_thenResponseStatusShouldBe200() {
        Response response = dinosaurResource.addAdultDinosaur(aDinosaurDTO);

        assertEquals(STATUS_200_OK, response.getStatus());
    }

    @Test
    public void givenABreedingRequestDTO_whenBreedDinosaur_thenDinosaurShouldBeBred() {
        dinosaurResource.breedDinosaur(aBreedingRequestDTO);

        verify(dinosaurService).
                breedDinosaur(aBreedingRequestDTO.name, aBreedingRequestDTO.fatherName,
                        aBreedingRequestDTO.motherName);
    }

    @Test
    public void givenABreedingRequestDTO_whenBreedDinosaur_thenResponseStatusShouldBe200() {
        Response response = dinosaurResource.breedDinosaur(aBreedingRequestDTO);

        assertEquals(STATUS_200_OK, response.getStatus());
    }

    @Test
    public void givenADinosaurName_whenShowDinosaur_thenTheDinosaurDTOShouldBeCreated() {
        when(dinosaurService.showDinosaur(A_DINOSAUR_NAME)).thenReturn(aDinosaur);

        Response response = dinosaurResource.showDinosaur(A_DINOSAUR_NAME);
        DinosaurDTO expectedDinosaur = (DinosaurDTO)response.getEntity();

        assertEquals(expectedDinosaur.name, A_DINOSAUR_NAME);
        assertEquals(expectedDinosaur.weight, aDinosaurDTO.weight);
        assertEquals(expectedDinosaur.gender, aDinosaurDTO.gender);
    }

    @Test
    public void givenADinosaurName_whenShowDinosaur_thenResponseStatusShouldBe200() {
        when(dinosaurService.showDinosaur(A_DINOSAUR_NAME)).thenReturn(aDinosaur);

        Response response = dinosaurResource.showDinosaur(A_DINOSAUR_NAME);

        assertEquals(STATUS_200_OK, response.getStatus());
    }

    @Test
    public void whenShowAllDinosaurs_thenAllDinosaursShouldBeShown() {
        dinosaurResource.showAllDinosaurs();

        verify(dinosaurService).showAllDinosaurs();
    }

    @Test
    public void givenMultipleDinosaurs_whenShowAllDinosaurs_thenTheDinosaursDTOShouldBeCreated() {
        dinosaurs.add(aDinosaur);
        dinosaurs.add(anotherDinosaur);
        when(dinosaurService.showAllDinosaurs()).thenReturn(dinosaurs);

        Response response = dinosaurResource.showAllDinosaurs();

        assertTrue(response.hasEntity());
    }

    @Test
    public void whenShowAllDinosaurs_thenResponseStatusShouldBe200() {
        Response response = dinosaurResource.showAllDinosaurs();

        assertEquals(STATUS_200_OK, response.getStatus());
    }

    @Test
    public void whenShowAllDinosaurs_thenTheServiceShouldBeCalled() {
        dinosaurResource.showAllDinosaurs();

        verify(dinosaurService).showAllDinosaurs();
    }

    @Test
    public void givenASumoRequestDTOWithValidData_whenSumoFight_thenShouldPrepareSumoFight() {
        dinosaurResource.sumoFight(aSumoRequestDTO);

        verify(dinosaurService).
                prepareSumoFight(aSumoRequestDTO.challenger, aSumoRequestDTO.challengee);
    }

    @Test
    public void givenASumoRequestDTOWithValidData_whenSumoFight_thenResponseStatusShouldBe200() {
        Response response = dinosaurResource.sumoFight(aSumoRequestDTO);

        assertEquals(STATUS_200_OK, response.getStatus());
    }

    @Test
    public void givenASumoRequestDTO_whenSumoFight_thenSumoResponseDTOShouldBeCreated() {
        String expectedPredictedWinner = dinosaurService.prepareSumoFight(aSumoRequestDTO.challenger,
                aSumoRequestDTO.challengee);
        Response response = dinosaurResource.sumoFight(aSumoRequestDTO);
        SumoResponseDTO sumoResponseDTO = (SumoResponseDTO)response.getEntity();

        assertEquals(expectedPredictedWinner, sumoResponseDTO.predictedWinner);
    }

    @Test
    public void givenADinosaurNameAndAGrowDTO_whenUpdateDinosaur_thenTheServiceShouldBeCalledWithThoseArguments() {
        dinosaurResource.updateDinosaur(A_DINOSAUR_NAME, aGrowDTO);

        verify(dinosaurService).updateDinosaurWeight(A_DINOSAUR_NAME, A_WEIGHT);
    }

    @Test
    public void givenADinosaurNameAndAGrowDTO_whenUpdateDinosaur_thenResponseStatusShouldBe200() {
        Response response = dinosaurResource.updateDinosaur(A_DINOSAUR_NAME, aGrowDTO);

        assertEquals(STATUS_200_OK, response.getStatus());
    }
}
