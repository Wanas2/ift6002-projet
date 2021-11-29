package ca.ulaval.glo4002.game.domain;

import ca.ulaval.glo4002.game.domain.action.AddAdultDinosaurAction;
import ca.ulaval.glo4002.game.domain.action.AddFoodAction;
import ca.ulaval.glo4002.game.domain.action.ExecutableAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

class TurnTest {

    private static final int INITIAL_TURN_NUMBER = 1;

    private ExecutableAction aFirstAction;
    private ExecutableAction aSecondAction;
    private Turn turn;

    @BeforeEach
    void setUp() {
        aFirstAction = mock(AddFoodAction.class);
        aSecondAction = mock(AddAdultDinosaurAction.class);
        turn = new Turn();
    }

    @Test
    public void givenANewTurn_whenPlayActions_thenTheTurnNumberShouldBeOne() {
        int turnNumber = turn.playActions();

        assertEquals(INITIAL_TURN_NUMBER, turnNumber);
    }

    @Test
    public void givenMultipleActionsHaveBeenAcquired_whenPlayActions_thenActionsShouldBeExecutedInOrderOfAcquisition() {
        turn.acquireNewAction(aFirstAction);
        turn.acquireNewAction(aSecondAction);
        InOrder fromFirstToLastOrder = inOrder(aFirstAction, aSecondAction);

        turn.playActions();

        fromFirstToLastOrder.verify(aFirstAction).execute();
        fromFirstToLastOrder.verify(aSecondAction).execute();
    }

    @Test
    public void givenActions_whenPlayActions_thenTurnShouldHaveNoActionsLeft() {
        turn.playActions();

        assertFalse(turn.hasActions());
    }

    @Test
    public void whenPlayActions_thenTheTurnNumberShouldIncreaseByOne() {
        turn.playActions();
        int expectedTurnNumber = 2;

        int currentTurnNumber = turn.playActions();

        assertEquals(expectedTurnNumber, currentTurnNumber);
    }

    @Test
    public void whenReset_thenTheTurnNumberIsSetToOne() {
        turn.reset();

        int turnNumberAfterReset = turn.playActions();
        assertEquals(INITIAL_TURN_NUMBER, turnNumberAfterReset);
    }

    @Test
    public void whenReset_thenTurnShouldHaveNoActions() {
        turn.acquireNewAction(aFirstAction);
        turn.acquireNewAction(aSecondAction);

        turn.reset();

        assertFalse(turn.hasActions());
    }
}
