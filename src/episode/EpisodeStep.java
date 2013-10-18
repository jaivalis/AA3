package episode;

import action.JointAction;
import agent.Agent;
import agent.Predator;
import agent.Prey;
import state.State;

public class EpisodeStep {
	private State s;
	private JointAction a;
    private double preyR;
    private double predR;
	private State s_prime;
	private double preyDiscounted;
    private double predDiscounted;
	
	public EpisodeStep(State s, JointAction a, double preyR, double predR, State s_prime) {
		this.s = s;
		this.a = a;
        this.preyR = preyR;
        this.predR = predR;
		this.s_prime = s_prime;
	}
	
	public State getS() { return s; }
	public JointAction getA() { return a; }
    public double getPreyR() { return preyR; }
    public double getPredR() { return predR; }

    public double getReward(Agent a) {
        if (a instanceof Predator) {
            return this.predR;
        } if (a instanceof Prey) {
            return this.preyR;
        } return Double.NEGATIVE_INFINITY;
    }

	public void setDiscounted(double preyDiscounted, double predDiscounted) {
        this.preyDiscounted = preyDiscounted;
        this.predDiscounted = predDiscounted;
    }
}
