package agent;

import action.Action.action;
import policy.Policy;
import policy.Q;
import policy.RandomPreyPolicy;
import state.State;
import util.Coordinates;

import java.util.Random;


public class Prey extends Agent {

    /**
     * Creates a prey set to location 5,5 with a randomPreyPolicy
     */
    public Prey(Coordinates c, Q q, Policy p) { super(c, q, p); }

//	public Prey(Coordinates c) { this.coordinates = c; }
	
	/** copy constructor */
//	public Prey(Prey p) { this.coordinates = p.coordinates; }

	@Override
	public String toString() { return "Prey (" + this.getCoordinates().getX() + ", " + this.getCoordinates().getY() + ")"; }

    @Override
    public action getAction(State s) {
        Random r = new Random();
        float f = r.nextFloat();
        if (f < 0.2) { // p_{trip} = 0.2
            return action.WAIT;
        } return this.policy.getAction(s);
    }
}
