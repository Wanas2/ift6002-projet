package ca.ulaval.glo4002.game.applicationService;

import ca.ulaval.glo4002.game.applicationService.food.ResourceService;
import ca.ulaval.glo4002.game.domain.Game;
import ca.ulaval.glo4002.game.domain.GameFactory;
import ca.ulaval.glo4002.game.domain.GameRepository;
import ca.ulaval.glo4002.game.domain.food.Food;
import ca.ulaval.glo4002.game.domain.food.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ResourceServiceTest {

    private ResourceService foodService;

    private Game game;

    @BeforeEach
    void setUp() {
        GameRepository gameRepository = mock(GameRepository.class);
        GameFactory gameFactory = mock(GameFactory.class);
        foodService = new ResourceService(gameRepository, gameFactory);

        game = mock(Game.class);
        when(gameRepository.find()).thenReturn(Optional.of(game));
    }

    @Test
    public void givenSomeFood_whenAddFood_thenGameShouldAddTheFood() {
        List<Food> someFood = List.of(mock(Food.class), mock(Food.class));

        foodService.addFood(someFood);

        verify(game).addFood(someFood);
    }

    @Test
    public void whenGetFoodQuantitySummary_thenSummaryShouldBeCalculated() {
        Pantry pantry = mock(Pantry.class);
        when(game.getPantry()).thenReturn(pantry);

        foodService.getFoodQuantitySummary();

        verify(pantry).obtainFoodHistory();
    }
}
