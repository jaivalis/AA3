package state;

import action.Action;
import action.JointAction;
import agent.Agent;
import agent.Predator;
import agent.Prey;
import util.Coordinates;

import java.util.ArrayList;

public abstract class State implements Cloneable {
    protected Coordinates preyC;
    protected ArrayList<Coordinates> predC;
    protected double stateValue;	// Corresponds to value V (for the policy evaluation algorithm).

    public double getStateValue() {	return this.stateValue; }

    public double getPreyReward() {
        if (preyIsCaught())     { return -10.0; }
        if (predatorsCollide()) { return 10.0; }
        return 0.0;
    }

    public double getPredatorReward() {
        return -this.getPreyReward();
    }

    public double getReward(Prey agent) {
    	return this.getPreyReward();
    }

    public double getReward(Predator agent) {
    	return this.getPredatorReward();
    }
    
    public double getReward(Agent agent) {
    	new Exception("should not get there, should call getReward(Prey) or getReward(Predator)").printStackTrace();
    	System.exit(0);
		return 0;
    }

    /**
     * @return true if predator coordinates match prey coordinates, false otherwise.
     */
    private boolean preyIsCaught() { // TODO: handle predC collide on prey coordinates.
        for (Coordinates predator : this.predC) {
            if (this.preyC.equals(predator)) { return true; }
        } return false;
    }

    /**
     * @return true if two predC share coordinates, false otherwise.
     */
    private boolean predatorsCollide() {
        switch (this.predC.size()) {
            case 1:
                return false;
            case 2:
                return this.predC.get(0).equals(this.predC.get(1));
            case 3:
                return (this.predC.get(0).equals(this.predC.get(1)) ||
                        this.predC.get(1).equals(this.predC.get(2)) ||
                        this.predC.get(0).equals(this.predC.get(2)));
            default:
                Exception ex = new Exception();
                ex.printStackTrace();
                System.exit(-1);
        } return false;
    }

    public void setStateValue(double stateValue) { this.stateValue = stateValue; }

	@Override
	public String toString() {
		String ret = "";
		ret += "Value = " + this.stateValue + " Prey : " + this.preyC;
        for (Coordinates predator : this.predC) { ret +=  " Predator : " + predator; }
		return ret;
	}

    @Override
	public boolean equals(Object other) {
		if (other == null) { return false; }
	    if (other == this) { return true; }
	    if (!(other instanceof State)) { return false; }

	    State otherState = (State) other;

        int thisP = this.predC.size();
        int otherP = otherState.predC.size();
        if (thisP != otherP) { return false; }
        for (int i = 0 ; i < thisP; i ++ ){ // FIXME prominent bug depending on State implementation
            if (this.predC.get(i) != otherState.predC.get(i)) { return false; }
        }
        return this.preyC == otherState.preyC;
    }

    @Override
    public int hashCode() {
    	String hashString = "1" + this.preyC.getX() + this.preyC.getY();
        for (Coordinates predator : this.predC) {
            hashString += predator.getX() + predator.getY();
        } return Integer.parseInt(hashString);
    }

    public Coordinates getPreyCoordinates() { return this.preyC; }
    public ArrayList<Coordinates> getPredatorCoordinates() { return this.predC; }

    public boolean isTerminal() { return preyIsCaught() || predatorsCollide(); }

    /**
     * Alters the current state to the state that will occur after a joint action is taken.
     * @param ja the JointAction to be taken.
     */
    public void nextState(JointAction ja) {
        // 'move' prey
        Action.action preyAction = ja.preyAction;
        for (int i = 0; i < this.predC.size(); i++) { // move all predators in the opposite direction.
            Coordinates currCoord = this.predC.get(0);
			Coordinates newCoord = currCoord.createShifted(preyAction.getOpposite());
			this.predC.set(i, newCoord);
        }
        
        // move predators according to their actions.
        int index = 0;
        for (int i = 0; i < this.predC.size(); i++) { // move all predators in the opposite direction.
            Coordinates currCoord = this.predC.get(i);
			Coordinates newCoord = currCoord.createShifted(ja.predatorActions.get(i));
			this.predC.set(i, newCoord);
        }
    }
    
    public State clone() {
    	try {
			return (State) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
			return null;
		}
    }
}