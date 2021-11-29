package ca.ulaval.glo4002.game.domain.action;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.herd.Herd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SumoFightActionTest {

    private Dinosaur aDinosaur;
    private Dinosaur anotherDinosaur;
    private Herd herd;
    private SumoFightAction sumoFightAction;

    @BeforeEach
    public void setUp() {
        anotherDinosaur = mock(Dinosaur.class);
        aDinosaur = mock(Dinosaur.class);
        herd = mock(Herd.class);
        sumoFightAction = new SumoFightAction(herd, aDinosaur, anotherDinosaur);
    }

    @Test
    public void whenExecute_thenHerdShouldOrganizeSumoFight() {
        sumoFightAction.execute();

        verify(herd).organizeSumoFight(aDinosaur, anotherDinosaur);
    }
}
