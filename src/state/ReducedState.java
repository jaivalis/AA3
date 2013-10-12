package state;

import util.Coordinates;

import java.util.ArrayList;

/**
 * In this implementation of the state space the Preys position is set to (5,5), the only one moving is the predator and
 * when the prey 'moves' the predator will move to the opposite direction instead.
 */
public class ReducedState extends State {
    public ReducedState(ArrayList<Coordinates> pred) {
        this.preyC = new Coordinates(5, 5);
        this.predC = pred;
    }

    /**
     * Random state constructor
     */
    public ReducedState(int predators) {
        this.predC = new ArrayList<>();
        this.preyC = new Coordinates(5, 5);
        for (int i = 0; i < predators; i++) {
            this.predC.add(new Coordinates());
        }
    }
}
