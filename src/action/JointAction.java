package action;

import java.util.HashMap;

import state.MinimalState;
import state.State;
import action.Action.action;
import agent.Agent;
import agent.AgentsCollection;
import agent.Predator;
import agent.Prey;

public class JointAction {
    // TODO make all private, use getter
    public action preyAction;
    public HashMap<Integer, action> predatorActions;

    public HashMap<Agent, action> actionsMap;
    /**
     * Creates a joint action of all the agents based on the current state.
     * @param s Current state.
     * @param p Prey instance.
     * @param predators Contains the predators.
     */
    public JointAction(State s, AgentsCollection agents) {
        this.actionsMap = new HashMap<>();
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

    /**
     * Creates a joint action of all the agents based on the current state.
     * @param s Current state.
     */
    public JointAction(MinimalState s, AgentsCollection agents) {
        this.actionsMap = new HashMap<>();
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

    public action get(Agent a) {
        return actionsMap.get(a);
    }
}
