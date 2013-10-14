package episode;

import action.JointAction;
import agent.AgentsCollection;
import agent.Predator;
import agent.Prey;
import state.ReducedState;
import state.State;

import java.util.ArrayList;

public class EpisodeGenerator {
    private AgentsCollection agents;

    public EpisodeGenerator(AgentsCollection agents) {
        this.agents = agents;
    }

    /**
     * Creates an instance of episode using a given policy for the Predator.
     * @param initialState
     * @param gamma
     * @return
     */
	public Episode generate(ReducedState initialState, double gamma) {
    	Episode episode = new Episode(this.agents);

        ReducedState s = initialState;
        ReducedState s_prime = initialState;

        int steps = 0;
        System.out.println("step #"+steps + " " + s);
        do {
        	steps++;
            JointAction ja = new JointAction(s, this.agents);

            s_prime = s.nextState(ja);  // s <- s.getSuccessor()

            double preyReward = s_prime.getPreyReward();
            double predReward = s_prime.getPredatorReward();
            episode.addStep(s, ja, preyReward, predReward, s_prime);
            s = s_prime;
            System.out.println("step #"+steps + " " + s);
        } while(!s_prime.isTerminal() && steps < 1000000);

        episode.refreshDiscounted(gamma);
		return episode;
	}
}
