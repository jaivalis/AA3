package policy;

import action.Action;
import action.LearnedAction;
import action.PossibleActions;
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
		return ac.getRandomAction();
	}

	public double getActionProbability(State s, Action.action a) {
		return this.stateActionMapping.get(s).getActionProbability(a);
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
//    public void printMaxActionsGrid() {
//		State[][] states = new State[11][11];
//		for(State s : this.stateActionMapping.keySet()){
//			Coordinates c = s.getPreyCoordinates();
//			states[c.getX()][c.getY()] = s;
//		}
//		for(int i = 0; i < 11; i++){
//			for(int j = 0; j < 11; j++){
//				action a = this.getArgMaxActionProbability(states[i][j]);
//				System.out.print(a.getArrow() + "\t");
//				// System.out.print(this.board[i][j].getStateValue() + "\t");
//			}
//			System.out.println();
//		}
//	}
}
