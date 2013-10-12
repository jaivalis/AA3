package policy;

import action.Action;
import state.State;

import java.util.Random;
import action.Action.action;

public class RandomPreyPolicy extends Policy {

    @Override
    public Action.action getAction(State s) {
        Random r = new Random();
        float randfloat = r.nextFloat();
        if (randfloat < 0.8)	{ return action.WAIT; }
        if (randfloat < 0.85)	{ return action.NORTH; }
        if (randfloat < 0.9)	{ return action.SOUTH; }
        if (randfloat < 0.95)	{ return action.EAST; }
        return action.WEST;
    }

    @Override
    public double getActionProbability(State s, Action.action a) {
        switch (a) {
            case WAIT:  return 0.8;
            case NORTH: case SOUTH: case EAST: case WEST:  return 0.05;
        }
        return -1;
    }
}
