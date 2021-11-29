package ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal;

import ca.ulaval.glo4002.game.domain.dinosaur.*;
import ca.ulaval.glo4002.game.domain.dinosaur.consumption.FoodConsumptionStrategy;
import ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.dto.BabyDinosaurResponseDTO;
import ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.dto.BreedingRequestExternalDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.WebTarget;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BabyFetcherFromExternalAPITest {

    private static final String BABY_NAME = "Marie";
    private static final String THE_FEMALE_GENDER = Gender.F.toString();
    private static final String A_DINOSAUR_SPECIES = Species.Spinosaurus.toString();

    private BabyFetcherFromExternalAPI aBabyFetcher;
    private DinosaurBreederExternal externalBreeder;
    private DinosaurFactory factory;
    private AdultDinosaur aMaleDinosaur;
    private AdultDinosaur aFemaleDinosaur;
    private BabyDinosaur aBabyDinosaur;

    @BeforeEach
    public void setup() {
        externalBreeder = mock(DinosaurBreederExternal.class);
        factory = mock(DinosaurFactory.class);
        ParentsGenderValidator validator = mock(ParentsGenderValidator.class);

        String maleName = "Bob";
        String femaleName = "Helene";
        int weight = 17;

        aBabyFetcher = new BabyFetcherFromExternalAPI(externalBreeder, factory, validator);
        aMaleDinosaur = new AdultDinosaur(Species.Spinosaurus, weight, maleName, Gender.M,
                mock(FoodConsumptionStrategy.class));
        aFemaleDinosaur = new AdultDinosaur(Species.Spinosaurus, weight, femaleName, Gender.F,
                mock(FoodConsumptionStrategy.class));
        aBabyDinosaur = new BabyDinosaur(Species.Spinosaurus, BABY_NAME, Gender.M,
                mock(FoodConsumptionStrategy.class), aMaleDinosaur, aFemaleDinosaur);
    }

    @Test
    public void givenAFatherAndAMotherDinosaur_whenFetch_thenShouldReturnABaby()
            throws SpeciesWillNotBreedException {
        BabyDinosaurResponseDTO responseDTO =
                new BabyDinosaurResponseDTO(THE_FEMALE_GENDER, A_DINOSAUR_SPECIES);
        when(externalBreeder.breed(any(WebTarget.class), any(BreedingRequestExternalDTO.class)))
                .thenReturn(responseDTO);
        when(factory.createBaby(responseDTO.gender, responseDTO.offspring, BABY_NAME,
                aMaleDinosaur, aFemaleDinosaur)).thenReturn(aBabyDinosaur);

        Optional<BabyDinosaur> babyDinosaur = aBabyFetcher.fetch(aMaleDinosaur, aFemaleDinosaur, BABY_NAME);

        assertTrue(babyDinosaur.isPresent());
    }

    @Test
    public void givenAFatherAndAMotherDinosaurThatWontBreed_whenFetch_thenShouldNotReturnABaby()
            throws SpeciesWillNotBreedException {
        when(externalBreeder.breed(any(WebTarget.class), any(BreedingRequestExternalDTO.class)))
                .thenThrow(new SpeciesWillNotBreedException());

        Optional<BabyDinosaur> babyDinosaur = aBabyFetcher.fetch(aMaleDinosaur, aFemaleDinosaur, BABY_NAME);

        assertTrue(babyDinosaur.isEmpty());
    }
}
