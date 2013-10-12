package action;

import action.Action.action;
import agent.Predator;
import agent.Prey;
import state.State;

import java.util.ArrayList;
import java.util.HashMap;

public class JointAction {
    public action preyAction;
    public HashMap<Integer, action> predatorActions;

    /**
     * Creates a joint action of all the agents based on the current state.
     * @param s Current state.
     * @param p Prey instance.
     * @param predators Contains the predators.
     */
    public JointAction(State s, Prey p, ArrayList<Predator> predators) {
        this.preyAction = p.getAction(s);

        int i = 0;
        predatorActions = new HashMap<>();
        for (Predator pred : predators) {
            predatorActions.put(i++, pred.getAction(s));
        }
    }
}
