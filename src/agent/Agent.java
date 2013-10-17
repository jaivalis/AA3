package agent;

import action.Action;
import util.Coordinates;
import policy.Policy;
import policy.Q;
import state.State;

public abstract class Agent {
	protected Coordinates coordinates;
    protected Policy policy;
    public Q q;

    public Agent(Coordinates c, Q q, Policy p) {
        this.q = q;
        this.coordinates = c;
        this.policy = p;
    }

    /** Copy constructor */
    public Agent(Agent a) {
        this.q = a.q;
        this.coordinates = a.coordinates;
        this.policy = a.policy;
    }

	public Coordinates getCoordinates() { return this.coordinates; }
	
	/**
	 * Move the specific actor to the given coordinates.
	 * @param c Coordinates to move Actor to.
	 */
	public void setCoordinates(Coordinates c) { this.coordinates = c; }
	/**
	 * Updates the coordinates according to the action taken.
	 */
	private void move(Action.action a) {
        this.coordinates = this.coordinates.createShifted(a);
    }
	/**
	 * Move the Actor according to an action picked from the policy of the Actor.
	 * @param s The current state.
	 */
	public void move(State s) { this.move(this.policy.getAction(s)); }

    /**
     * @param policy The policy to be set.
     */
	public void setPolicy(Policy policy) {
        this.policy = policy;
    }

	/**
	 * Returns the action the actor would take in state s.
	 * @param s The state in question.
	 */
	public Action.action getAction(State s) { return this.policy.getAction(s); }

    /**
     * Given state s and action a, returns the probability of the agent to take that action
     * @param s a state
     * @param a an action
     * @return the probability of the agent to take the given action
     */
    public double getActionProbability(State s, Action.action a) {
        return this.policy.getActionProbability(s, a);
    }

	public Q getQ() {
		return this.q;
	}
}
