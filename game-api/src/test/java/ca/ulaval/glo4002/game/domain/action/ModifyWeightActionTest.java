package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ModifyWeightActionTest {

    private static final int WEIGHT_VARIATION = 110;

    private ModifyWeightAction modifyWeightAction;
    private Dinosaur dinosaur;


    @BeforeEach
    public void setUp() {
        dinosaur = mock(Dinosaur.class);
        modifyWeightAction = new ModifyWeightAction(WEIGHT_VARIATION,dinosaur);
    }

    @Test
    public void givenADinosaurAndAWeightVariation_WhenExecute_thenDinosaurWeightIsModified() {
        modifyWeightAction.execute();

        verify(dinosaur).modifyWeight(WEIGHT_VARIATION);
    }
}
