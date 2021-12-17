package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddAdultDinosaurActionTest {

    private Dinosaur dinosaur;
    private Herd herd;
    private AddAdultDinosaurAction addAdultDinosaurAction;

    @BeforeEach
    public void setUp() {
        dinosaur = mock(Dinosaur.class);
        herd = mock(Herd.class);
        addAdultDinosaurAction = new AddAdultDinosaurAction(herd, dinosaur);
    }

    @Test
    public void givenADinosaur_WhenExecute_thenDinosaurIsAddedToHerd() {
        addAdultDinosaurAction.execute();

        verify(herd).addDinosaur(dinosaur);
    }
}
