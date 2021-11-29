package ca.ulaval.glo4002.game.domain.food.foodDistribution;

import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.FoodType;
import ca.ulaval.glo4002.game.domain.food.FoodTypesNotMatchingException;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WaterSplitter {

    private List<Food> waterForCarnivorous = new LinkedList<>();
    private List<Food> waterForHerbivorous = new LinkedList<>();
    private Map<Integer, Integer> waterLeftOutAfterSplit = new HashMap<>();

    public void splitWater(List<Food> allFreshFood) {
        Predicate<Food> mustBeWater = foodFiltered->foodFiltered.getType().equals(FoodType.WATER);
        List<Food> allWater = allFreshFood.stream()
                .filter(mustBeWater)
                .collect(Collectors.toList());

        for(Food batchOfWater: allWater) {
            int splitQuantityOfWater = splitBatchOfWaterInTwo(batchOfWater);
            Food food1 = new Food(FoodType.WATER, splitQuantityOfWater, batchOfWater.getAge());
            Food food2 = new Food(FoodType.WATER, splitQuantityOfWater, batchOfWater.getAge());
            waterForCarnivorous.add(food1);
            waterForHerbivorous.add(food2);
        }

        allFreshFood.removeAll(allWater);
    }

    public void mergeWater(List<Food> allFreshFood) {
        List<Food> allMergedWaterBatches = new LinkedList<>(waterForCarnivorous);

        for(Food waterBatch: allMergedWaterBatches) {
            addMatchingWaterBatchOfHerbivorous(waterBatch);
            int quantityOfWaterOfMatchingAgeLeftAfterSplit =
                    waterLeftOutAfterSplit.getOrDefault(waterBatch.getAge(), 0);
            waterBatch.increaseQuantity(quantityOfWaterOfMatchingAgeLeftAfterSplit);
            waterLeftOutAfterSplit.remove(waterBatch.getAge());
        }
        if(!waterLeftOutAfterSplit.isEmpty()) {
            waterLeftOutAfterSplit.forEach((age, quantity)->allFreshFood.add(new Food(FoodType.WATER, quantity, age)));
        }
        allMergedWaterBatches.addAll(waterForHerbivorous);
        allFreshFood.addAll(allMergedWaterBatches);
        resetWaterSplitter();
        allFreshFood.sort(Comparator.comparing(Food::getAge).reversed());
    }

    public List<Food> getWaterForCarnivorous() {
        return waterForCarnivorous;
    }

    public List<Food> getWaterForHerbivorous() {
        return waterForHerbivorous;
    }

    private int splitBatchOfWaterInTwo(Food batchOfWater) {
        if(batchOfWater.quantity()%2 != 0) {
            waterLeftOutAfterSplit.put(batchOfWater.getAge(), batchOfWater.quantity()%2);
        }
        return batchOfWater.quantity()/2;
    }

    private void addMatchingWaterBatchOfHerbivorous(Food waterBatchToAddTo) {
        Optional<Food> matchingWaterBatch =
                getWaterBatchOfMatchingAge(waterForHerbivorous, waterBatchToAddTo.getAge());

        matchingWaterBatch.ifPresent(
                waterBatch->{
                    try {
                        waterBatchToAddTo.increaseQuantity(waterBatch);
                        waterForHerbivorous.remove(waterBatch);
                    } catch(FoodTypesNotMatchingException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private Optional<Food> getWaterBatchOfMatchingAge(List<Food> waterBatches, int requiredAge) {
        Predicate<Food> mustBeOfRequiredAge = foodFiltered->foodFiltered.getAge() == requiredAge;

        return waterBatches.stream()
                .filter(mustBeOfRequiredAge)
                .findFirst();
    }

    private void resetWaterSplitter() {
        waterLeftOutAfterSplit = new HashMap<>();
        waterForCarnivorous = new LinkedList<>();
        waterForHerbivorous = new LinkedList<>();
    }
}
