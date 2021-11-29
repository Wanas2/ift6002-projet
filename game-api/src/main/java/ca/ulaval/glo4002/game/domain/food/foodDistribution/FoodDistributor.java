package ca.ulaval.glo4002.game.domain.food.foodDistribution;

import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.FoodHistory;
import ca.ulaval.glo4002.game.domain.food.FoodType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FoodDistributor {

    public int distributeExactOrMostPossibleFoodAsked(FoodType foodTypeToProvide, List<Food> allFreshFood,
                                                      int requestedQuantity, FoodHistory foodHistory) {
        int totalFoodGivenQuantity = 0;
        int remainingFoodQuantityToProvide = requestedQuantity;
        Predicate<Food> mustBeOfFoodTypeToProvide = foodFiltered->foodFiltered.getType().equals(foodTypeToProvide);

        List<Food> allFoodsMatchingFoodTypeToProvide = allFreshFood.stream()
                .filter(mustBeOfFoodTypeToProvide)
                .collect(Collectors.toList());

        List<Food> allFoodsProvided = new ArrayList<>();
        for(Food food: allFoodsMatchingFoodTypeToProvide) {
            if(remainingFoodQuantityToProvide <= food.quantity()) {
                food.decreaseQuantity(remainingFoodQuantityToProvide);
                foodHistory.
                        increaseConsumedQuantity(new Food(food.getType(), remainingFoodQuantityToProvide));
                totalFoodGivenQuantity += remainingFoodQuantityToProvide;
                break;
            } else {
                foodHistory.increaseConsumedQuantity(food);
                totalFoodGivenQuantity += food.quantity();
                remainingFoodQuantityToProvide -= food.quantity();

                allFoodsProvided.add(food);
            }
        }

        allFreshFood.removeAll(allFoodsProvided);
        return totalFoodGivenQuantity;
    }
}
