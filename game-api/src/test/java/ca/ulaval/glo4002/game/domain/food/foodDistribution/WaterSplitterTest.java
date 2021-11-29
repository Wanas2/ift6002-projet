package ca.ulaval.glo4002.game.domain.food.foodDistribution;

import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WaterSplitterTest {

    private static final int AN_ODD_QUANTITY = 945;
    private static final int AN_EVEN_QUANTITY = 628;
    private static final int ANOTHER_EVEN_QUANTITY = 1494;
    private static final int AN_AGE = 2;
    private static final int ANOTHER_AGE = 6;

    private List<Food> allFreshWater;
    private Food some_water;
    private WaterSplitter waterSplitter;

    @BeforeEach
    void setUp() {
        waterSplitter = new WaterSplitter();
        allFreshWater = new LinkedList<>();
    }

    @Test
    public void initiallyBothWaterContainersAreEmpty() {
        assertTrue(waterSplitter.getWaterForCarnivorous().isEmpty());
        assertTrue(waterSplitter.getWaterForHerbivorous().isEmpty());
    }

    @Test
    public void givenFreshWater_whenSplitWater_thenBothWaterContainersNowContainsWater() {
        some_water = new Food(FoodType.WATER, AN_EVEN_QUANTITY);
        allFreshWater.add(some_water);

        waterSplitter.splitWater(allFreshWater);

        assertFalse(waterSplitter.getWaterForCarnivorous().isEmpty());
        assertFalse(waterSplitter.getWaterForHerbivorous().isEmpty());
    }

    @Test
    public void givenSomeBatchesOfWater_whenSplitWater_thenAllBatchesAreSplitIntoBothContainers() {
        int expectedSizeOfWaterContainer = 2;
        Food batchOfWater1 = new Food(FoodType.WATER, AN_EVEN_QUANTITY, AN_AGE);
        Food batchOfWater2 = new Food(FoodType.WATER, AN_EVEN_QUANTITY, ANOTHER_AGE);
        allFreshWater.add(batchOfWater1);
        allFreshWater.add(batchOfWater2);

        waterSplitter.splitWater(allFreshWater);

        assertEquals(expectedSizeOfWaterContainer, waterSplitter.getWaterForCarnivorous().size());
        assertEquals(expectedSizeOfWaterContainer, waterSplitter.getWaterForHerbivorous().size());
    }

    @Test
    public void givenAnEvenQuantityOfWater_whenSplitWater_thenTheWaterIsSplitEquallyIntoBothContainers() {
        int expectedQuantityOfWater = AN_EVEN_QUANTITY/2;
        Food batchOfWater1 = new Food(FoodType.WATER, AN_EVEN_QUANTITY, AN_AGE);
        allFreshWater.add(batchOfWater1);

        waterSplitter.splitWater(allFreshWater);
        int waterForCarnivorousQuantity =
                waterSplitter.getWaterForCarnivorous().stream().mapToInt(Food::quantity).sum();
        int waterForHerbivorousQuantity =
                waterSplitter.getWaterForHerbivorous().stream().mapToInt(Food::quantity).sum();

        assertEquals(expectedQuantityOfWater, waterForCarnivorousQuantity);
        assertEquals(expectedQuantityOfWater, waterForHerbivorousQuantity);
    }

    @Test
    public void givenAnOddQuantityOfWater_whenSplitWater_thenTheWaterSplitEquallyIntoBothContainers() {
        int expectedQuantityOfWater = AN_ODD_QUANTITY/2;
        Food batchOfWater1 = new Food(FoodType.WATER, AN_ODD_QUANTITY, AN_AGE);
        allFreshWater.add(batchOfWater1);

        waterSplitter.splitWater(allFreshWater);
        int waterForCarnivorousQuantity =
                waterSplitter.getWaterForCarnivorous().stream().mapToInt(Food::quantity).sum();
        int waterForHerbivorousQuantity =
                waterSplitter.getWaterForHerbivorous().stream().mapToInt(Food::quantity).sum();

        assertEquals(expectedQuantityOfWater, waterForCarnivorousQuantity);
        assertEquals(expectedQuantityOfWater, waterForHerbivorousQuantity);
    }

    @Test
    public void givenMultipleBatchesOfWater_whenSplitWater_thenAllBatchesAreSplitEquallyIntoBothContainers() {
        int expectedQuantityOfWaterInFirstBatch = AN_EVEN_QUANTITY/2;
        int expectedQuantityOfWaterInSecondBatch = ANOTHER_EVEN_QUANTITY/2;
        Food batchOfWater1 = new Food(FoodType.WATER, AN_EVEN_QUANTITY, AN_AGE);
        Food batchOfWater2 = new Food(FoodType.WATER, ANOTHER_EVEN_QUANTITY, ANOTHER_AGE);
        allFreshWater.add(batchOfWater1);
        allFreshWater.add(batchOfWater2);

        waterSplitter.splitWater(allFreshWater);

        assertEquals(expectedQuantityOfWaterInFirstBatch, waterSplitter.getWaterForCarnivorous().get(0).quantity());
        assertEquals(expectedQuantityOfWaterInFirstBatch, waterSplitter.getWaterForHerbivorous().get(0).quantity());
        assertEquals(expectedQuantityOfWaterInSecondBatch, waterSplitter.getWaterForCarnivorous().get(1).quantity());
        assertEquals(expectedQuantityOfWaterInSecondBatch, waterSplitter.getWaterForHerbivorous().get(1).quantity());
    }

    @Test
    public void givenABatchOfWater_whenMergeWater_thenBothSplitWaterAreMergedBack() {
        Food batchOfWater1 = new Food(FoodType.WATER, AN_ODD_QUANTITY, AN_AGE);
        allFreshWater.add(batchOfWater1);

        waterSplitter.splitWater(allFreshWater);
        waterSplitter.mergeWater(allFreshWater);
        int quantityOfWaterAfterMerge = allFreshWater.stream().mapToInt(Food::quantity).sum();

        assertEquals(AN_ODD_QUANTITY, quantityOfWaterAfterMerge);
    }

    @Test
    public void givenWater_whenMergeWater_thenBothWaterContainersAreEmpty() {
        Food batchOfWater1 = new Food(FoodType.WATER, AN_ODD_QUANTITY, AN_AGE);
        allFreshWater.add(batchOfWater1);

        waterSplitter.splitWater(allFreshWater);
        waterSplitter.mergeWater(allFreshWater);

        assertTrue(waterSplitter.getWaterForCarnivorous().isEmpty());
        assertTrue(waterSplitter.getWaterForHerbivorous().isEmpty());
    }
}
