package action;

import action.Action.action;
import agent.AgentsCollection;
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
    public JointAction(State s, AgentsCollection agents) {
    	this.preyAction = agents.preys.get(0).getAction(s);

        int i = 0;
        predatorActions = new HashMap<>();
        for (Predator pred : agents.predators) {
            predatorActions.put(i++, pred.getAction(s));
        }
    }
}
