package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AddFoodActionTest {

    private Pantry pantry;
    private List<Food> foods;
    private AddFoodAction addFoodAction;

    @BeforeEach
    void setUp() {
        pantry = mock(Pantry.class);
        foods = new ArrayList<>();
        addFoodAction = new AddFoodAction(pantry, foods);
    }

    @Test
    public void givenFood_whenExecute_thenAddTheFoodToNewBatchOfFreshFood() {
        addFoodAction.execute();

        verify(pantry).obtainNewlyOrderedFoods(foods);
    }
}
