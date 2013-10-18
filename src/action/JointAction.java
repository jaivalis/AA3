package action;

import java.util.HashMap;

import state.State;
import action.Action.action;
import agent.Agent;
import agent.AgentsCollection;
import agent.Predator;
import agent.Prey;

public class JointAction {
    public action preyAction;
    public HashMap<Integer, action> predatorActions;

    public HashMap<Agent, action> actionsMap = new HashMap<Agent, action>();
    /**
     * Creates a joint action of all the agents based on the current state.
     * @param s Current state.
     * @param agents The agents.
     */
    public JointAction(State s, AgentsCollection agents) {
    	Prey prey = agents.preys.get(0);
    	this.preyAction = prey.getAction(s);
    	this.actionsMap.put(prey, this.preyAction);

        int i = 0;
        predatorActions = new HashMap<>();
        for (Predator pred : agents.predators) {
        	action act = pred.getAction(s);
            predatorActions.put(i++, act);
        	this.actionsMap.put(pred, act);
        }
    }
}
