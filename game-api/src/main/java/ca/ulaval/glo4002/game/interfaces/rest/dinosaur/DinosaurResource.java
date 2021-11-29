package ca.ulaval.glo4002.game.interfaces.rest.dinosaur;

import ca.ulaval.glo4002.game.applicationService.dinosaur.DinosaurService;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.assembler.DinosaurAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.assembler.SumoAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto.BreedingRequestDTO;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto.DinosaurDTO;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto.SumoRequestDTO;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.dto.SumoResponseDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DinosaurResource {

    private final DinosaurService dinosaurService;
    private final DinosaurAssembler dinosaurAssembler;
    private final SumoAssembler sumoAssembler;

    public DinosaurResource(DinosaurService dinosaurService, DinosaurAssembler dinosaurAssembler,
                            SumoAssembler sumoAssembler) {
        this.dinosaurService = dinosaurService;
        this.dinosaurAssembler = dinosaurAssembler;
        this.sumoAssembler = sumoAssembler;
    }

    @POST
    @Path("/dinosaurs")
    public Response addAdultDinosaur(DinosaurDTO dinosaurDTO) {
        dinosaurService.addAdultDinosaur(dinosaurDTO.name, dinosaurDTO.weight, dinosaurDTO.gender, dinosaurDTO.species);
        return Response.ok().build();
    }

    @POST
    @Path("/breed")
    public Response breedDinosaur(BreedingRequestDTO breedingRequestDTO) {
        dinosaurService.breedDinosaur(breedingRequestDTO.name, breedingRequestDTO.fatherName,
                breedingRequestDTO.motherName);
        return Response.ok().build();
    }

    @GET
    @Path("/dinosaurs/{name}")
    public Response showDinosaur(@PathParam("name") String name) {
        Dinosaur dinosaur = dinosaurService.showDinosaur(name);
        DinosaurDTO dinosaurDTO = dinosaurAssembler.toDTO(dinosaur);
        return Response.ok().entity(dinosaurDTO).build();
    }

    @GET
    @Path("/dinosaurs")
    public Response showAllDinosaurs() {
        List<Dinosaur> dinosaurs = dinosaurService.showAllDinosaurs();
        List<DinosaurDTO> dinosaurDTOs = dinosaurs.stream()
                .map(dinosaurAssembler::toDTO)
                .collect(Collectors.toList());

        return Response.ok().entity(dinosaurDTOs).build();
    }

    @POST
    @Path("/sumodino")
    public Response sumoFight(SumoRequestDTO sumoRequestDTO) {
        String predictedWinner = dinosaurService.prepareSumoFight(sumoRequestDTO.challenger,
                sumoRequestDTO.challengee);
        SumoResponseDTO sumoResponseDTO = sumoAssembler.toDTO(predictedWinner);
        return Response.ok().entity(sumoResponseDTO).build();
    }

    @PATCH
    @Path("/dinosaurs/{name}")
    public Response updateDinosaur(@PathParam("name") String name, GrowDTO growDTO) {
        dinosaurService.updateDinosaurWeight(name, growDTO.weight);
        return Response.ok().build();
    }
}
