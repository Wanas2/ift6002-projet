package ca.ulaval.glo4002.game.domain.food;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class FoodHistory {

    private Map<FoodType, Integer> freshFoodQuantities = new HashMap<>();
    private final Map<FoodType, Integer> expiredFoodQuantities = new HashMap<>();
    private final Map<FoodType, Integer> consumedFoodQuantities = new HashMap<>();

    public FoodHistory() {
        initializeExpiredFoodQuantities();
        initiateConsumedFoodQuantities();
    }

    public void increaseExpiredQuantity(Food food) {
        int currentFoodQuantity = expiredFoodQuantities.get(food.getType());
        int newFoodQuantity = currentFoodQuantity+food.quantity();
        expiredFoodQuantities.put(food.getType(), newFoodQuantity);
    }

    public void increaseConsumedQuantity(Food food) {
        int currentFoodQuantity = consumedFoodQuantities.get(food.getType());
        int newFoodQuantity = currentFoodQuantity+food.quantity();
        consumedFoodQuantities.put(food.getType(), newFoodQuantity);
    }

    public void computeFreshFoodQuantities(List<Food> allFreshFoods) {
        for(FoodType foodType: FoodType.values()) {
            Predicate<Food> foodTypeFilter = foodFiltered->foodFiltered.getType().equals(foodType);
            int foodQuantity = allFreshFoods.stream()
                    .filter(foodTypeFilter)
                    .mapToInt(Food::quantity).sum();

            freshFoodQuantities.put(foodType, foodQuantity);
        }
    }

    public void reset() {
        freshFoodQuantities = new HashMap<>();
        initiateConsumedFoodQuantities();
        initializeExpiredFoodQuantities();
    }

    public Map<FoodType, Integer> getFreshFoodQuantities() {
        return freshFoodQuantities;
    }

    public Map<FoodType, Integer> getExpiredFoodQuantities() {
        return expiredFoodQuantities;
    }

    public Map<FoodType, Integer> getConsumedFoodQuantities() {
        return consumedFoodQuantities;
    }

    private void initializeExpiredFoodQuantities() {
        expiredFoodQuantities.put(FoodType.BURGER, 0);
        expiredFoodQuantities.put(FoodType.SALAD, 0);
        expiredFoodQuantities.put(FoodType.WATER, 0);
    }

    private void initiateConsumedFoodQuantities() {
        consumedFoodQuantities.put(FoodType.BURGER, 0);
        consumedFoodQuantities.put(FoodType.SALAD, 0);
        consumedFoodQuantities.put(FoodType.WATER, 0);
    }
}
