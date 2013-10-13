package episode;

import action.JointAction;
import agent.AgentsCollection;
import agent.Predator;
import agent.Prey;
import state.State;

import java.util.ArrayList;

public class EpisodeGenerator {
    private AgentsCollection agents = new AgentsCollection();

    public EpisodeGenerator(int predatorCount) {
        this.agents.preys.add(new Prey());
        for (int i = 0; i < predatorCount; i++) {
            Predator p = new Predator(i);
            this.agents.predators.add(p);
        }
    }

    /**
     * Creates an instance of episode using a given policy for the Predator.
     * @param initialState
     * @param gamma
     * @return
     */
	public Episode generate(State initialState, double gamma) {
    	Episode episode = new Episode(this.agents);

        State s = initialState;
        State s_prime = initialState;

        int steps = 0;
        System.out.println("step #"+steps + " " + s);
        do {
        	steps++;
            JointAction ja = new JointAction(s, this.agents);
            s = s_prime;

            s_prime = s;
            s_prime.nextState(ja);  // s <- s.getSuccessor()

            double preyReward = s_prime.getPreyReward();
            double predReward = s_prime.getPredatorReward();
            episode.addStep(s, ja, preyReward, predReward, s_prime);
            System.out.println("step #"+steps + " " + s);
        } while(!s_prime.isTerminal() && steps < 1000000);

        episode.refreshDiscounted(gamma);
		return episode;
	}
}
