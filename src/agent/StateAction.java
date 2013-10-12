package agent;

import action.Action;
import state.State;

public class StateAction {
	private State s;
	private Action.action a;
	
	public StateAction(State s, Action.action a) {
		this.s = s;
		this.a = a;
	}

	public State getS() { return s; }
	
	public Action.action getA() { return a; }
	
	public String toString() {
		return "("+this.s.toString()+","+this.a.toString()+")";
	}
	
    @Override
	public boolean equals(Object other) {
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof StateAction)) { return false; }
	    StateAction otherStateAction = (StateAction) other;
		return this.s.equals(otherStateAction.getS()) && this.a.equals(otherStateAction.getA());
	}

    @Override
    public int hashCode() {
    	String hashString = "" + this.s.hashCode() + this.a.hashCode();
        return hashString.hashCode();
    }
}
