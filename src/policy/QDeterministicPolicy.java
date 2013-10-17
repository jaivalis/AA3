package policy;

import state.State;
import action.Action.action;
import action.LearnedAction;

public class QDeterministicPolicy extends QPolicy {
	public action getAction(State s) {
		return this.q.getArgmaxA(s);
	}
}
