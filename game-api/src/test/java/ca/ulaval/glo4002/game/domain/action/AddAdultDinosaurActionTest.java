package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.AdultDinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddAdultDinosaurActionTest {

    private AdultDinosaur dinosaur;
    private Herd herd;
    private AddAdultDinosaurAction addAdultDinosaurAction;

    @BeforeEach
    public void setUp() {
        dinosaur = mock(AdultDinosaur.class);
        herd = mock(Herd.class);
        addAdultDinosaurAction = new AddAdultDinosaurAction(herd, dinosaur);
    }

    @Test
    public void givenADinosaur_WhenExecute_thenDinosaurIsAddedToHerd() {
        addAdultDinosaurAction.execute();

        verify(herd).addAdultDinosaur(dinosaur);
    }
}
