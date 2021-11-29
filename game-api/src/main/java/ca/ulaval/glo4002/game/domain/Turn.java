package ca.ulaval.glo4002.game.domain;

import ca.ulaval.glo4002.game.domain.action.ExecutableAction;

import java.util.LinkedList;
import java.util.Queue;

public class Turn {

    private int turnNumber = 0;
    private Queue<ExecutableAction> actions = new LinkedList<>();

    public void acquireNewAction(ExecutableAction action) {
        actions.add(action);
    }

    public int playActions() {
        while(hasActions()) {
            ExecutableAction action = actions.remove();
            action.execute();
        }

        turnNumber++;
        return turnNumber;
    }

    public boolean hasActions() {
        return !actions.isEmpty();
    }

    public void reset() {
        actions = new LinkedList<>();
        turnNumber = 0;
    }
}
