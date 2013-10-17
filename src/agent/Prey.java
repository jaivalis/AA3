package agent;

import policy.Policy;
import policy.Q;
import policy.RandomPreyPolicy;
import state.State;
import util.Coordinates;


public class Prey extends Agent {

    /**
     * Creates a prey set to location 5,5 with a randomPreyPolicy
     */
    public Prey(Coordinates c, Q q, Policy p) {
        super(c, q, p);
    }

    /** copy constructor */
	public Prey(Prey p) { super(p); }

	@Override
	public String toString() { return "Prey (" + this.getCoordinates().getX() + ", " + this.getCoordinates().getY() + ")"; }

	@Override
	/**
	 * Moves prey with respect to the predator's position.
	 */
	public void move(State s) {
		Coordinates newC = this.coordinates;
		do {
			newC = newC.createShifted(this.policy.getAction(s));
		} while (s.getPredatorCoordinates().equals(newC));
		this.setCoordinates(newC);
	}
}
