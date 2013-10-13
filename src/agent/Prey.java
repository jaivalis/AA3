package agent;

import policy.RandomPreyPolicy;
import state.State;
import util.Coordinates;


public class Prey extends Agent {

    /**
     * Creates a prey set to location 5,5 with a randomPreyPolicy
     */
    public Prey() {
        this.policy = new RandomPreyPolicy();
        this.coordinates = new Coordinates(5, 5);
    }

	public Prey(Coordinates c) { this.coordinates = c; }
	
	/** copy constructor */
	public Prey(Prey p) { this.coordinates = p.coordinates; }

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
