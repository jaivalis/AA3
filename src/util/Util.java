package util;

import state.State;

import java.util.ArrayList;

/**
 * Static variables referenced in all parts of the code.
 */
public class Util {
    /* Task 1 */
	public final static int DIM = 11;               /* Dimension of the grid world. */
	public final static double PREYREWARD = 10.0;   /* Reward assigned to the Prey actor. */
    public static final int EPISODE_COUNT = 1000;
    /* Task 2 */
	public static double epsilon = 0.1;             /* Used in the Epsilon-Greedy implementation. */
    public static double tau = 4;             /* Softmax action selection temperature */

    public final static double MAX_ROUNDS = 10000;

    public ArrayList<State> getNeighbors(State s) {
        return null; // TODO
    }
}
