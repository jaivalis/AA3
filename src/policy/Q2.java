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
	public double get(State s, action a) {
		new Exception("cannot use get(State s, action a) in Q2").printStackTrace();
		System.exit(0);
		return -1;
	}

	@Override
	public void set(State s, action a, double d) {
		new Exception("cannot use set(State s, action a, double d) in Q2").printStackTrace();
		System.exit(0);
	}

	@Override
	public double get(State s, action a, action o) {
		HashSet<StateAction2> sa2_set = this.s_sa2.get(s);
		if(sa2_set == null) {
			sa2_set = new HashSet<StateAction2>();
			this.s_sa2.put(s, sa2_set);
		}
		
		for(StateAction2 sa2 : sa2_set) {
			if(sa2.getA().equals(a) && sa2.getO().equals(o)) {
				return this.sa2_d.get(sa2);
			}
		}
		
		// the value does not exist, let's create it set as initial settings
		this.set(s, a, o, this.initialQvalue);
		return this.initialQvalue;
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
