package ca.ulaval.glo4002.game.interfaces.rest.food;

import ca.ulaval.glo4002.game.applicationService.food.ResourceService;
import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.FoodHistory;
import ca.ulaval.glo4002.game.interfaces.rest.food.assembler.FoodAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.food.assembler.FoodSummaryAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.food.dto.FoodDTO;
import ca.ulaval.glo4002.game.interfaces.rest.food.dto.FoodSummaryDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/resources")
public class FoodResource {

    private final ResourceService resourceService;
    private final FoodValidator foodValidator;
    private final FoodAssembler foodAssembler;
    private final FoodSummaryAssembler foodSummaryAssembler;

    public FoodResource(ResourceService resourceService, FoodValidator foodValidator, FoodAssembler foodAssembler,
                        FoodSummaryAssembler foodSummaryAssembler) {
        this.resourceService = resourceService;
        this.foodValidator = foodValidator;
        this.foodAssembler = foodAssembler;
        this.foodSummaryAssembler = foodSummaryAssembler;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFood(FoodDTO foodDTO) {
        foodValidator.validateFoodEntries(foodDTO);
        List<Food> food = foodAssembler.fromDTO(foodDTO);
        resourceService.addFood(food);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFoodQuantitySummary() {
        FoodHistory foodHistory = resourceService.getFoodQuantitySummary();
        FoodSummaryDTO foodSummaryDTO = foodSummaryAssembler.toDTO(foodHistory);
        return Response.ok().entity(foodSummaryDTO).build();
    }
}
