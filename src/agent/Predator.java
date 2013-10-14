package agent;

import policy.Policy;
import policy.Q;
import policy.RandomPredatorPolicy;
import util.Coordinates;

public class Predator extends Agent {

    public Predator(Coordinates c, Q q, Policy p) {
        super(c, q, p);
    }
//    /**
//     * Constructor is used for multi-agent systems.
//     * @param id The number of the predator.
//     */
//    public Predator(int id) {
//        switch (id) {
//            case 0:
//                this.coordinates = new Coordinates(0, 0);
//                break;
//            case 1:
//                this.coordinates = new Coordinates(10, 0);
//                break;
//            case 2:
//                this.coordinates = new Coordinates(0, 10);
//                break;
//        }
//        this.policy = new RandomPredatorPolicy();
//    }

	@Override
	public String toString() { return "Predator (" + this.coordinates.getX() + ", " + this.coordinates.getY() + ")"; }
}
