package ca.ulaval.glo4002.game.domain.food;

import ca.ulaval.glo4002.game.domain.food.foodDistribution.FoodDistributor;
import ca.ulaval.glo4002.game.domain.food.foodDistribution.WaterSplitter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Pantry implements FoodStorage {

    private List<Food> currentTurnFoodBatch = new ArrayList<>();
    private List<Food> allFreshFoods = new LinkedList<>();
    private final FoodProvider foodProvider;
    private final FoodDistributor foodDistributor;
    private final WaterSplitter waterSplitter;
    private final FoodHistory foodHistory;

    public Pantry(FoodProvider foodProvider, FoodDistributor foodDistributor, WaterSplitter waterSplitter,
                  FoodHistory foodHistory) {
        this.foodProvider = foodProvider;
        this.foodDistributor = foodDistributor;
        this.waterSplitter = waterSplitter;
        this.foodHistory = foodHistory;
    }

    public void obtainNewlyOrderedFoods(List<Food> orderedFoods) {
        for(FoodType foodType: FoodType.values()) {
            Predicate<Food> mustBeOfOfCurrentFoodType = foodFiltered->foodFiltered.getType().equals(foodType);

            Optional<Food> foodOrderedOfMatchingType = orderedFoods.stream()
                    .filter(mustBeOfOfCurrentFoodType)
                    .findFirst();

            foodOrderedOfMatchingType.ifPresent(food->
                    addToMatchingFood(food, currentTurnFoodBatch));
        }
    }

    public void storeAllNewlyOrderedFoods() {
        List<Food> newBatchOfFood = new ArrayList<>();
        List<Food> foodFromProvider = foodProvider.provideFood();

        for(FoodType foodType: FoodType.values()) {
            Predicate<Food> mustBeOfOfCurrentFoodType = foodFiltered->foodFiltered.getType().equals(foodType);

            Optional<Food> foodFromCurrentTurnFoodBatch = currentTurnFoodBatch.stream()
                    .filter(mustBeOfOfCurrentFoodType)
                    .findFirst();
            foodFromCurrentTurnFoodBatch.ifPresent(food
                    ->addToMatchingFood(food, newBatchOfFood));

            Optional<Food> foodFromFoodProvider = foodFromProvider.stream()
                    .filter(mustBeOfOfCurrentFoodType)
                    .findFirst();
            foodFromFoodProvider.ifPresent(food
                    ->addToMatchingFood(food, newBatchOfFood));
        }

        allFreshFoods.addAll(newBatchOfFood);
        currentTurnFoodBatch = new ArrayList<>();
    }

    public void incrementFreshFoodAges() {
        List<Food> allFoodsToRemove = new ArrayList<>();
        for(Food food: allFreshFoods) {
            food.incrementAgeByOne();
            if(food.isExpired()) {
                foodHistory.increaseExpiredQuantity(food);
                allFoodsToRemove.add(food);
            }
        }
        allFreshFoods.removeAll(allFoodsToRemove);
    }

    public void splitWaterInTwo() {
        waterSplitter.splitWater(allFreshFoods);
    }

    @Override
    public int giveExactOrMostPossibleSaladDesired(int requestedSaladQuantity) {
        return foodDistributor.distributeExactOrMostPossibleFoodAsked(FoodType.SALAD, allFreshFoods,
                requestedSaladQuantity, foodHistory);
    }

    @Override
    public int giveExactOrMostPossibleBurgerDesired(int requestedBurgerQuantity) {
        return foodDistributor
                .distributeExactOrMostPossibleFoodAsked(FoodType.BURGER, allFreshFoods, requestedBurgerQuantity,
                        foodHistory);
    }

    @Override
    public int giveExactOrMostPossibleWaterDesiredToCarnivorous(int requestedWaterQuantity) {
        List<Food> waterForCarnivorous = waterSplitter.getWaterForCarnivorous();
        return foodDistributor
                .distributeExactOrMostPossibleFoodAsked(FoodType.WATER, waterForCarnivorous, requestedWaterQuantity,
                        foodHistory);
    }

    @Override
    public int giveExactOrMostPossibleWaterDesiredToHerbivorous(int requestedWaterQuantity) {
        List<Food> waterForHerbivorous = waterSplitter.getWaterForHerbivorous();
        return foodDistributor
                .distributeExactOrMostPossibleFoodAsked(FoodType.WATER, waterForHerbivorous, requestedWaterQuantity,
                        foodHistory);
    }

    public void mergeWater() {
        waterSplitter.mergeWater(allFreshFoods);
    }

    public FoodHistory obtainFoodHistory() {
        foodHistory.computeFreshFoodQuantities(allFreshFoods);
        return foodHistory;
    }

    public void reset() {
        allFreshFoods = new LinkedList<>();
        foodHistory.reset();
    }

    public List<Food> getAllFreshFoods() {
        return allFreshFoods;
    }

    private void addToMatchingFood(Food foodToAdd, List<Food> foodsToAddTo) {
        Predicate<Food> mustBeOfRequiredFoodType = foodFiltered->foodFiltered.getType().equals(foodToAdd.getType());
        Predicate<Food> mustBeOfRequiredFoodAge = foodFiltered->foodFiltered.getAge() == foodToAdd.getAge();

        createEmptyMatchingFoodIsNotPresent(foodsToAddTo, foodToAdd.getType(), foodToAdd.getAge());

        Optional<Food> matchingFoodToAddTo = foodsToAddTo.stream()
                .filter(mustBeOfRequiredFoodType.and(mustBeOfRequiredFoodAge))
                .findFirst();

        matchingFoodToAddTo.ifPresent((food->{
            try {
                food.increaseQuantity(foodToAdd);
            } catch(FoodTypesNotMatchingException e) {
                e.printStackTrace();
            }
        }));
    }

    private void createEmptyMatchingFoodIsNotPresent(List<Food> foods, FoodType requiredFoodType,
                                                     int requiredFoodAge) {
        Predicate<Food> mustBeOfRequiredFoodType = foodFiltered->foodFiltered.getType().equals(requiredFoodType);
        Predicate<Food> mustBeOfRequiredFoodAge = foodFiltered->foodFiltered.getAge() == requiredFoodAge;

        Optional<Food> matchingFood = foods.stream()
                .filter(mustBeOfRequiredFoodType.and(mustBeOfRequiredFoodAge))
                .findFirst();

        if(matchingFood.isEmpty()) {
            foods.add(new Food(requiredFoodType, 0));
        }
    }
}
