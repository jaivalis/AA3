package policy;

import action.Action;
import action.LearnedAction;
import action.PossibleActions;
import action.PossibleActionsConcrete;
import agent.StateAction;
import state.State;

import java.util.HashMap;

public abstract class Policy {
    protected HashMap<StateAction, Double> stateActionProbability;
    protected HashMap<State, Action.action> policy;
	protected HashMap<State, PossibleActions> stateActionMapping; // necessary?
	
	protected Policy() {
		this.stateActionMapping = new HashMap<>();
	}
	
	/**
	 * Chooses an stochastic action, according to the probabilities associated.
	 * @param s
	 * @return
	 */
	public Action.action getAction(State s) {
        PossibleActions ac = this.stateActionMapping.get(s);
        if(ac == null) { // lazy instantiation
        	ac = new PossibleActionsConcrete();
        }
        this.stateActionMapping.put(s,ac);
		return ac.getRandomAction();
	}

	/**
	 * gets action with maximum probability of being chosen, given a state
	 * @param s
	 * @return
	 */
	public Action.action getArgMaxActionProbability(State s) {
        Action.action argmax_a = null;
		double max = Double.NEGATIVE_INFINITY;
		for(Action.action a : Action.action.values()) {
			Double p = this.stateActionMapping.get(s).getActionProbability(a);
			if(p>max) {
				max = p;
				argmax_a = a;
			}
		}
		return argmax_a;
	}

	/**
	 * For State s, set action probability of Action a to 1.0 and all the rest to 0.
	 * (Deterministic policy) 
	 */
	public void setUniqueAction(State s, Action.action a) {
		if (a == null) { return; }
		if(!this.stateActionMapping.containsKey(s)){
			this.stateActionMapping.put(s, new LearnedAction());
		}
		this.stateActionMapping.get(s).setAllActionProbabilitiesTo(0.0);
		this.stateActionMapping.get(s).setActionProbability(a, 1.0);
	}

	public void initializeStateValues(double d) {
		for (State state : this.stateActionMapping.keySet()) {
			state.setStateValue(d);
		}
	}

    /**
     * Initializes all State actions to a given arbitrary action.
     * @param ac The action to which all the states are initialized.
     */
    public void initializeActionsArbitrarily(Action.action ac) {
        for (State state : this.stateActionMapping.keySet()) {
            this.stateActionMapping.get(state).makeActionDeterministic(ac);
        }
    }

    /**
     * Initializes all State actions to a given arbitrary action.
     */
    public void initializeActionsAsRandom() {
        for (State state : this.stateActionMapping.keySet()) {
            Action.action ac = Action.action.getRandom();
            this.stateActionMapping.get(state).makeActionDeterministic(ac);
        }
    }

    public double getActionProbability(State s, Action.action a) {
        return this.stateActionMapping.get(s).getActionProbability(a);
    }
}
