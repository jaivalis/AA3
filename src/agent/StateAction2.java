package agent;

import action.Action;
import action.Action.action;
import state.State;

/**
 * For MiniMaxQ
 * @author stablum
 *
 */
public class StateAction2 {
	private State s;
	private Action.action a;
	private Action.action o;
	
	public StateAction2(State s, Action.action a, Action.action o) {
		this.s = s;
		this.a = a;
		this.o = o;
	}

	public State getS() { return s; }
	
	public Action.action getA() { return a; }

	public Action.action getO() { return o; }

	public String toString() {
		return "("+this.s.toString()+","+this.a.toString()+","+this.o.toString()+")";
	}
	
    @Override
	public boolean equals(Object other) {
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof StateAction2)) { return false; }
	    StateAction2 otherStateAction = (StateAction2) other;
		return this.s.equals(otherStateAction.getS()) && this.a.equals(otherStateAction.getA()) && this.o.equals(otherStateAction.getO());
	}

    @Override
    public int hashCode() {
    	String hashString = "" + this.s.hashCode() + this.a.hashCode() + this.o.hashCode();
        return hashString.hashCode();
    }
}
