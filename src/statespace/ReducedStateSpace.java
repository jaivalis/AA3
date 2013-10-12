package statespace;

import action.Action.action;
import action.JointAction;
import policy.Policy;
import state.ReducedState;
import state.State;
import util.Coordinates;

import java.util.*;

/**
 * For this StateSpace we make the assumption that the prey stays in a fixed position and only the predator moves.
 */
public class ReducedStateSpace extends StateSpace implements Iterable<State>, Iterator<State> {
    private int iter_pos;

    public ReducedStateSpace(boolean initialize) {
        if (initialize) {
            this.states = new HashSet<>();
            this.initStates();
        }
    }

    @Override
    protected void initStates() {
        for (int i = 0; i < util.Util.DIM; i++) {
            for (int j = 0; j < util.Util.DIM; j++) {
                ArrayList<Coordinates> predators = new ArrayList<>();
                predators.add(new Coordinates(i, j));
                this.states.add(new ReducedState(predators)); // preyCoordinates fixed to (0,0)
            }
        }
    }

//    @Override
//    public ReducedState getState(Coordinates preyC, Coordinates predC) {
//    	// FIXME: this function may be removed!!!!!
//        return this.states[preyC.getX()][preyC.getY()];
//    }

    public ReducedState getState(Coordinates preyC) {
        return this.states[preyC.getX()][preyC.getY()];
    }

//    @Override
//    public Algorithms.action getTransitionAction(State s, State s_prime) {
//        ReducedState rs = (ReducedState) s;
//        ReducedState rs_prime = (ReducedState) s_prime;
//        return rs.getTransitionAction(rs_prime);
//    }

    @Override
    public void initializeStateValues(double d) {
        for (State s : this) { s.setStateValue(d); }
    }

    @Override
    public void printActions(Policy policy) {
        // TODO
    }

    @Override
    public State getNextState(State s, JointAction a) {
//        Coordinates predNew = s.getPredatorCoordinates().get(0);
//        predNew = predNew.getShifted(a.getOpposite());
//        return this.getState(s.getPreyCoordinates(), predNew);
        return null;
    }

//    @Override
//    public ProbableTransitions getProbableTransitions(State s, action a) {
//        ProbableTransitions ret = new ProbableTransitions();
//
//        // if the action will be undertaken, then the predator
//        // will have a new position, BUT since we are in a system
//        // in which the relative position predator-prey is relevant
//        // and the predator is considered to stay always in (0,0)
//        // then we need to consider the prey as virtually moving, even if it's not.
//        // This virtual move is in the opposite direction of the actual predator move
//        Coordinates preyNewPos = s.getPreyCoordinates().getShifted(a.getOpposite());
//
//        // afterwards, there is the *actual* move of the prey
//        // where could the prey be going?
//        for(action act : action.values()) {
//            PreyAction tmp = new PreyAction();
//            double p = tmp.getActionProbability(act);
//            Coordinates preyPossiblePos = preyNewPos.getShifted(act);
//            ret.add(this.getState(preyPossiblePos), p);
//        }
//        return ret;
//    }
    
    /******************************* Iterator Related ************************************/
    @Override
    public Iterator iterator() {
        this.resetIterator();
        return this;
    }

    private void resetIterator() { this.iter_pos = 0; }

    @Override
    public boolean hasNext() { return this.iter_pos < (Math.pow(util.Util.DIM, 2)); }

    @Override
    public ReducedState next() {
        if(this.hasNext()){
            int tmp = this.iter_pos;
            int j = tmp % util.Util.DIM;
            tmp = tmp / util.Util.DIM;
            int i = tmp % util.Util.DIM;
            this.iter_pos++;
            return this.states[i][j];
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() { }
    /******************************* Iterator Related ************************************/

	@Override
	public ArrayList<State> getNeighbors(State s) {
		ArrayList<State> ret = new ArrayList<>();
//		for (action a : action.values()) {
//			ret.add(this.getNextState(s, a)); TODO
//		}
		return ret;
	}

    @Override
    public State getRandomState() {
        Random r = new Random();
        int randomInt = r.nextInt(length());
        int tmp = randomInt;
        int j = tmp % util.Util.DIM;
        tmp = tmp / util.Util.DIM;
        int i = tmp % util.Util.DIM;
        this.iter_pos++;
        return this.states[i][j];
    }

    @Override
    protected int length() { return (int)Math.pow(util.Util.DIM, 2); }
}