package policy;

import action.Action;
import state.State;

import action.Action.action;

import java.util.Random;

public class RandomPredatorPolicy extends Policy {

    @Override
    public action getAction(State s) {
        Random r = new Random();
        float randfloat = r.nextFloat();
        if (randfloat < 0.2)	{ return action.WAIT; }
        if (randfloat < 0.4)	{ return action.NORTH; }
        if (randfloat < 0.6)	{ return action.SOUTH; }
        if (randfloat < 0.8)	{ return action.EAST; }
        return action.WEST;
    }

    @Override
    public double getActionProbability(State s, Action.action a) {
        return 0.2;
    }
}
