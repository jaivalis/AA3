package policy;

import state.State;
import util.Util;
import action.Action.action;
import action.PossibleActions;
import action.PossibleActionsConcrete;

public class QEpsilonGreedyPolicy extends QPolicy {

    @Override
	public action getAction(State s) {
		
		if(this.q == null) {
			new Exception("EpsilonGreedyPolicy: this.q is not set. Please call setQ before!").printStackTrace();
			System.exit(0);
		}
		
		Double epsilon_frac = Util.epsilon/((double)action.values().length);
		PossibleActions possibleActions = this.stateActionMapping.get(s);
		if(possibleActions == null) {
			possibleActions = new PossibleActionsConcrete();
			this.stateActionMapping.put(s, possibleActions);
		}
		
		// set all probabilities to epsilon divided by number of actions
		possibleActions.setAllActionProbabilitiesTo(epsilon_frac);
		
		// find maximum action and set it to the greedy probability value (1 - epsilon + epsilon/size(A))
		action max_a = this.q.getArgmaxA(s);
		if(max_a == null){
			// FIXME: do something here!!
		}
		double greedy_prob = 1 - Util.epsilon + epsilon_frac;
		possibleActions.setActionProbability(max_a, greedy_prob);
		
		// stochastic query to get action for state s
		return super.getAction(s);
	}
}