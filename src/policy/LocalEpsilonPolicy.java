package policy;

import state.State;
import action.Action;
import action.LearnedAction;

public class LocalEpsilonPolicy extends Policy {

	/**
	 * @Override
	 * For State s, set action probability of Action a to 1-epsilon+epsilon_frac and all the rest to epsilon_frac.
	 * (Non-deterministic epsilon-greedy policy)
	 * Similar to setUniqueAction, with the difference that the epsilon
	 * is local to the state (and not stored) 
	 */
	public void setEpsilonAction(State s, double epsilon, Action.action a) {
		if (a == null) { return; }
		if(!this.stateActionMapping.containsKey(s)){
			this.stateActionMapping.put(s, new LearnedAction());
		}
		Double epsilon_frac = epsilon /((double)Action.action.values().length);
		double greedy_prob = 1 - epsilon + epsilon_frac;

		this.stateActionMapping.get(s).setAllActionProbabilitiesTo(epsilon_frac);
		this.stateActionMapping.get(s).setActionProbability(a, greedy_prob);
	}
}
