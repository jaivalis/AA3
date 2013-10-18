package policy;

import action.Action;
import action.Action.action;
import action.PossibleActions;
import action.PossibleActionsConcrete;
import agent.StateAction;
import state.State;
import util.Util;

import java.util.HashMap;
import java.util.HashSet;

public class WoLFPolicy extends Policy {
    protected HashMap<StateAction, Double> X;
    protected HashMap<StateAction, Double> Z;

    public WoLFPolicy() {
        this.X = new HashMap<>();
        this.Z = new HashMap<>();
    }

    @Override
    public Action.action getAction(State s) {
        Double epsilon_frac = Util.epsilon/((double) Action.action.values().length);
        PossibleActions possibleActions = this.stateActionMapping.get(s);
        if(possibleActions == null) {
            possibleActions = new PossibleActionsConcrete();
            this.stateActionMapping.put(s, possibleActions);
        }

        // set all probabilities to epsilon divided by number of actions
        possibleActions.setAllActionProbabilitiesTo(epsilon_frac);

        // find maximum action and set it to the greedy probability value (1 - epsilon + epsilon/size(A))
        Action.action max_a = this.getArgmaxX(s);
        if(max_a == null){
            // FIXME: do something here!!
        }
        double greedy_prob = 1 - Util.epsilon + epsilon_frac;
        possibleActions.setActionProbability(max_a, greedy_prob);

        // stochastic query to get action for state s
        return super.getAction(s);
    }

    public double getX(StateAction sa) { return this.X.get(sa); }
    public double getZ(StateAction sa) { return this.Z.get(sa); }

    /**
     * Normalizes all the action probabilities
     */
    public void normalizeΧ() {
        double maxX_t = Double.NEGATIVE_INFINITY;
        for (StateAction sa : this.X.keySet()) {
            maxX_t = Math.max(maxX_t, this.X.get(sa));
        }
        for (StateAction sa : this.X.keySet()) {
            double old = this.X.get(sa);
            this.X.put(sa, old / maxX_t);
        }
    }

    /**
     * Given state s and action a, returns the probability of the agent to take that action
     * @param s a state
     * @param a an action
     * @return the probability of the agent to take the given action
     */
    public double getActionProbability(State s, Action.action a) {
        return this.X.get(new StateAction(s, a));
    }

    /**
     * Normalizes all the action probabilities
     */
    public void normalizeZ() {
        double maxZ_t = Double.NEGATIVE_INFINITY;
        for (StateAction sa : this.Z.keySet()) {
            maxZ_t = Math.max(maxZ_t, this.Z.get(sa));
        }
        for (StateAction sa : this.Z.keySet()) {
            double old = this.Z.get(sa);
            this.Z.put(sa, old / maxZ_t);
        }
    }

    public void setX(StateAction sa, double d) {
        this.X.put(sa, d);
    }

    public void setZ(StateAction sa, double d) {
        this.Z.put(sa, d);
    }

    /**
     * Returns the action that maximizes the return in state s.
     * @param s; State in question.
     * @return action; The optimal action.
     */
    public action getArgmaxX(State s) {
        double max = Double.NEGATIVE_INFINITY;
        HashSet<StateAction> sa_set = this.getStateActions(s);
        Action.action argmax_a = null;
        for(StateAction sa : sa_set) {
            Double val = this.X.get(sa);
            if(val > max) {
                max = val;
                argmax_a = sa.getA();
            }
        }
        if(argmax_a == null) {
            new Exception("value returned by getArgmaxA is null!!!").printStackTrace();
        }
        return argmax_a;
    }

    /**
     * Returns an arrayList containing all the stateAction pairs associated to a state.
     * @param s; State in question
     * @return ArrayList<StateAction>; List of StateActions
     */
    public HashSet<StateAction> getStateActions(State s) {
        HashSet<StateAction> sa_set = new HashSet<>();
        for (action a : action.values()) {
            sa_set.add(new StateAction(s, a));
        }
        return sa_set;
    }
}
