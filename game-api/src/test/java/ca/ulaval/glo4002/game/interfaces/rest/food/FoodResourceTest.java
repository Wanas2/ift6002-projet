package ca.ulaval.glo4002.game.interfaces.rest.food;

import ca.ulaval.glo4002.game.applicationService.food.ResourceService;
import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.FoodHistory;
import ca.ulaval.glo4002.game.domain.food.FoodType;
import ca.ulaval.glo4002.game.interfaces.rest.food.assembler.FoodAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.food.assembler.FoodSummaryAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.food.dto.FoodDTO;
import ca.ulaval.glo4002.game.interfaces.rest.food.dto.FoodSummaryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.when;

class FoodResourceTest {

    private final static int STATUS_200_OK = 200;
    private final static int A_QUANTITY_OF_BURGER = 3;
    private final static int A_QUANTITY_OF_SALAD = 2;
    private final static int A_QUANTITY_OF_WATER_IN_LITERS = 10;

    private List<Food> freshFood;
    private FoodHistory foodHistory;
    private ResourceService resourceService;
    private FoodValidator foodValidator;
    private FoodAssembler foodAssembler;
    private FoodSummaryAssembler foodSummaryAssembler;
    private FoodResource foodResource;

    @BeforeEach
    void setUp() {
        freshFood = new ArrayList<>();
        foodHistory = new FoodHistory();
        resourceService = mock(ResourceService.class);
        foodValidator = mock(FoodValidator.class);
        foodAssembler = new FoodAssembler();
        foodSummaryAssembler = new FoodSummaryAssembler(foodAssembler);
        foodResource = new FoodResource(resourceService, foodValidator, foodAssembler, foodSummaryAssembler);
    }

    @Test
    public void givenAFoodDTO_whenAddFood_thenResponseStatusShouldBe200() {
        FoodDTO aFoodDTO = new FoodDTO(A_QUANTITY_OF_BURGER, A_QUANTITY_OF_SALAD, A_QUANTITY_OF_WATER_IN_LITERS);

        Response response = foodResource.addFood(aFoodDTO);

        assertEquals(STATUS_200_OK, response.getStatus());
    }

    @Test
    public void whenGetFoodQuantitySummary_thenShouldGetFoodQuantitySummary() {
        initializeFreshFood();
        foodHistory.computeFreshFoodQuantities(freshFood);
        when(resourceService.getFoodQuantitySummary()).thenReturn(foodHistory);

        foodResource.getFoodQuantitySummary();

        verify(resourceService).getFoodQuantitySummary();
    }

    @Test
    public void whenGetFoodQuantitySummary_thenFoodSummaryDTOShouldBeCreated() {
        initializeFreshFood();
        foodHistory.computeFreshFoodQuantities(freshFood);
        when(resourceService.getFoodQuantitySummary()).thenReturn(foodHistory);

        Response response = foodResource.getFoodQuantitySummary();
        FoodSummaryDTO foodSummaryDTO = (FoodSummaryDTO)response.getEntity();
        FoodSummaryDTO expectedFoodSummaryDTO = foodSummaryAssembler.toDTO(foodHistory);

        assertEquals(expectedFoodSummaryDTO.fresh.qtyBurger, foodSummaryDTO.fresh.qtyBurger);
        assertEquals(expectedFoodSummaryDTO.fresh.qtySalad, foodSummaryDTO.fresh.qtySalad);
        assertEquals(expectedFoodSummaryDTO.fresh.qtyWater, foodSummaryDTO.fresh.qtyWater);
    }

    @Test
    public void whenGetFoodQuantitySummary_thenResponseStatusShouldBe200() {
        initializeFreshFood();
        foodHistory.computeFreshFoodQuantities(freshFood);
        when(resourceService.getFoodQuantitySummary()).thenReturn(foodHistory);

        Response response = foodResource.getFoodQuantitySummary();

        assertEquals(STATUS_200_OK, response.getStatus());
    }

    private void initializeFreshFood() {
        freshFood.add(new Food(FoodType.BURGER, 15));
    }
}
