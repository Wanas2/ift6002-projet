package ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal;

import ca.ulaval.glo4002.game.domain.dinosaur.BabyDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.BabyFetcher;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.dto.BabyDinosaurResponseDTO;
import ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal.dto.BreedingRequestExternalDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Optional;

public class BabyFetcherFromExternalAPI implements BabyFetcher {

    private final static String EXTERNAL_BREEDER_URI = "http://localhost:8080/breed";
    private final DinosaurBreederExternal dinosaurBreeder;
    private final DinosaurFactory dinosaurFactory;
    private final ParentsGenderValidator parentsGenderValidator;
    private final Client client = ClientBuilder.newClient();

    public BabyFetcherFromExternalAPI(DinosaurBreederExternal dinosaurBreeder, DinosaurFactory dinosaurFactory,
                                      ParentsGenderValidator parentsGenderValidator) {
        this.dinosaurBreeder = dinosaurBreeder;
        this.dinosaurFactory = dinosaurFactory;
        this.parentsGenderValidator = parentsGenderValidator;
    }

    @Override
    public Optional<BabyDinosaur> fetch(Dinosaur fatherDinosaur, Dinosaur motherDinosaur, String name) {
        parentsGenderValidator.validateParentGender(fatherDinosaur, motherDinosaur);

        BreedingAssembler breedingAssembler = new BreedingAssembler();
        BreedingRequestExternalDTO breedingRequestExternalDTO = breedingAssembler.toDTO(fatherDinosaur, motherDinosaur);

        BabyDinosaurResponseDTO babyDinosaurResponseDTO;

        try {
            babyDinosaurResponseDTO
                    = dinosaurBreeder.breed(client.target(EXTERNAL_BREEDER_URI).path("/"), breedingRequestExternalDTO);
        } catch(SpeciesWillNotBreedException e) {
            return Optional.empty();
        }

        String genderName = babyDinosaurResponseDTO.gender;
        String speciesName = babyDinosaurResponseDTO.offspring;

        BabyDinosaur babyDinosaur
                = dinosaurFactory.createBaby(genderName, speciesName, name, fatherDinosaur, motherDinosaur);
        return Optional.of(babyDinosaur);
    }
}
