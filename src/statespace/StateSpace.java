package statespace;

import action.Action.action;
import action.JointAction;
import agent.Predator;
import agent.Prey;
import policy.Policy;
import state.State;
import util.Coordinates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public abstract class StateSpace implements Iterable<State>, Iterator<State>  {
    protected HashSet<State> states;
    /****************************** abstract functions ****************************/
    /**
     * Called from the constructor. Initializes the state Container
     */
    protected abstract void initStates();

    /**
     * Returns the state the corresponds to the grid defined by the arguments.
     * @param preyC; Prey coordinates.
     * @param predC; Prey coordinates.
     */
    public abstract State getState(Coordinates preyC, Coordinates predC);

    public abstract void initializeStateValues(double d);

    public abstract void printActions(Policy policy);
    /****************************** /abstract functions ****************************/


    /**
     * returns the new State that occurs after predator takes action a.
     */
    public abstract State getNextState(State s, JointAction a);

//    /**
//     * Returns the Reward that would be acquired if action a was taken in State s.
//     * @param s; Current state.
//     * @param a; Action to be taken.
//     */
//    public double getActionReward(State s, Algorithms.action a) {
//        State newState = this.getNextState(s, a);
//        return newState.getStateReward();
//    }

//    /**
//     * returns a structure that contains all possible states associated
//     * with the predator in state s, wanting to perform action a
//     * @param s state of the predator
//     * @param a action that the predator might perform
//     * @return ProbableTransition a structure containing possible states and probabilities
//     */
//    public abstract ProbableTransitions getProbableTransitions(State s, action a);
    
//    /**
//     * Given a current state and an action hat has been chosen by the agent,
//     * produce a stochastically determined new state that belongs to the set
//     * of states that that action might lead to.
//     * In other words, this is the environment deciding the agent's next state.
//     */
//    // Creates a new successor State to the current one (after all the Agents make moves)
//    public State produceStochasticTransition(State s, JointAction a) {
//        State currS = s;
////		Random rand = new Random();
////		float randfl = rand.nextFloat();
////		ProbableTransitions probableTransitions = this.getProbableTransitions(s, a);
////		Iterator<State> itS = probableTransitions.getStates().iterator();
////		float sum = 0.0f;
////		do {
////			if(!itS.hasNext()){
////				// Exception is not thrown, just printed with stacktrace and program is aborted
////				new Exception("probabilities do not seem to sum up to 1.0").printStackTrace();
////				System.exit(0);
////			}
////			currS = itS.next();
////			sum += probableTransitions.getProbability(currS);
////		} while(randfl > sum);
//		return s.getSuccessorState();
//    }

	public abstract ArrayList<State> getNeighbors(State s);

    /**
     * Returns a random state from the StateSpace used for Q-Learing.
     */
    public abstract State getRandomState();

    protected abstract int length();

    /**
     * Returns a state
     * @param s
     * @param ja
     * @return
     */
    public State getSuccessorState(State s, JointAction ja) {
        s.nextState(ja);
        State nextState = s;
        this.states.add(nextState); // will be added if not already contained.
        return nextState;
    }
}
