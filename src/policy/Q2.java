package policy;

import java.util.HashMap;
import java.util.HashSet;

import state.State;
import action.Action.action;
import agent.StateAction;
import agent.StateAction2;

/**
 * variant of the Q structure, used in the MiniMaxQ algorithm
 * It uses two actions, a and o, associated to a state s,
 * to store a Q-value
 * @author stablum
 *
 */
public class Q2 extends Q {
	HashMap<StateAction2, Double> sa2_d = new HashMap<>();
    HashMap<State, HashSet<StateAction2>> s_sa2 = new HashMap<>();

	public Q2(double initialQvalue) {
		super(initialQvalue);
	}

	@Override
	public double get(State s, action a, action o) {
		new Exception("Q.get(s,a,o): use subclass Q2!").printStackTrace();
		System.exit(0);
		return 0;
	}
	
	@Override
	public void set(State s, action a, action o, double d) {
		StateAction2 sa2 = new StateAction2(s, a, o);
		this.sa2_d.put(sa2, d);
		
		// structure for cheap state-action retrieval
		HashSet<StateAction2> sa2_set = this.s_sa2.get(s);
		if(sa2_set == null) {
			sa2_set = new HashSet<>();
		}
        sa2_set.add(sa2);
		this.s_sa2.put(s, sa2_set);
	}
}
