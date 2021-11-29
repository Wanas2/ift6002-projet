package ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal;

import ca.ulaval.glo4002.game.domain.dinosaur.Gender;
import ca.ulaval.glo4002.game.domain.dinosaur.Species;
import ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.dto.BabyDinosaurResponseDTO;
import ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.dto.BreedingRequestExternalDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DinosaurBreederExternalTest {

    private final static int STATUS_200_OK = 200;
    private final static int STATUS_400_BAD_REQUEST = 400;
    private final static String A_FATHER_SPECIES = Species.Diplodocus.toString();
    private final static String A_MOTHER_SPECIES = Species.Ankylosaurus.toString();
    private final static String A_GENDER = Gender.F.toString();
    private final static String AN_OFFSPRING = "anOffspring";

    private DinosaurBreederExternal dinosaurBreederExternal;
    private BreedingRequestExternalDTO breedingRequestExternalDTO;
    private WebTarget externalService;
    private Response responseOfExternalService;
    private Invocation.Builder invocationBuilder;

    @BeforeEach
    void setUp() {
        externalService = mock(WebTarget.class);
        responseOfExternalService = mock(Response.class);
        invocationBuilder = mock(Invocation.Builder.class);
        when(externalService.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(responseOfExternalService);

        dinosaurBreederExternal = new DinosaurBreederExternal();
        breedingRequestExternalDTO = new BreedingRequestExternalDTO(A_FATHER_SPECIES, A_MOTHER_SPECIES);
    }

    @Test
    public void whenBreed_thenInvocationBuilderShouldPostTheEntity() throws SpeciesWillNotBreedException {
        dinosaurBreederExternal.breed(externalService, breedingRequestExternalDTO);

        ArgumentMatcher<Entity<BreedingRequestExternalDTO>> isTheEntityWithJsonMediaTypeAndContainingTheDTO =
                entity->entity.getEntity().equals(breedingRequestExternalDTO) &&
                        entity.getMediaType().toString().equals(MediaType.APPLICATION_JSON);
        verify(invocationBuilder).post(argThat(isTheEntityWithJsonMediaTypeAndContainingTheDTO));
    }

    @Test
    public void givenExternalServiceAnswersWithOk_whenBreed_thenTheBabyResponseDTOFromTheResponseShouldBeReturned() throws SpeciesWillNotBreedException {
        BabyDinosaurResponseDTO expectedBabyDinosaurResponseDTO = new BabyDinosaurResponseDTO(A_GENDER, AN_OFFSPRING);
        when(responseOfExternalService.getStatus()).thenReturn(STATUS_200_OK);
        when(responseOfExternalService.readEntity(BabyDinosaurResponseDTO.class))
                .thenReturn(expectedBabyDinosaurResponseDTO);

        BabyDinosaurResponseDTO fetchedBabyDinosaurResponseDTO
                = dinosaurBreederExternal.breed(externalService, breedingRequestExternalDTO);

        assertEquals(expectedBabyDinosaurResponseDTO, fetchedBabyDinosaurResponseDTO);
    }

    @Test
    public void givenExternalServiceAnswersWithBadRequest_whenBreed_thenSpeciesWillNotBreedExceptionShouldBeThrown() {
        when(responseOfExternalService.getStatus()).thenReturn(STATUS_400_BAD_REQUEST);

        assertThrows(SpeciesWillNotBreedException.class,
                ()->dinosaurBreederExternal.breed(externalService, breedingRequestExternalDTO));
    }
}
