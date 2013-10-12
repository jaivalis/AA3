package episode;

import action.JointAction;
import agent.Predator;
import agent.Prey;
import state.State;
import statespace.StateSpace;

import java.util.ArrayList;

public class EpisodeGenerator {

    private ArrayList<Predator> predators;
    private Prey prey;

    public EpisodeGenerator(int predatorCount) {
        this.prey = new Prey();
        this.predators = new ArrayList<>();
        for (int i = 0; i < predatorCount; i++) {
            Predator p = new Predator(i);
            predators.add(p);
        }
    }

    /**
     * Creates an instance of episode using a given policy for the Predator.
     * @param stateSpace
     * @param initialState
     * @param gamma
     * @return
     */
	public Episode generate(StateSpace stateSpace, State initialState, double gamma) {
    	Episode episode = new Episode(prey, predators);

        State s = initialState;
        State s_prime = initialState;

        int steps = 0;
        do {
        	steps++;
            JointAction ja =  new JointAction(s, this.prey, this.predators);
            s = s_prime;
            s_prime = stateSpace.getSuccessorState(s, ja);
            double preyReward = s_prime.getPreyReward();
            double predReward = s_prime.getPredatorReward();
            episode.addStep(s, ja, preyReward, predReward, s_prime);
        } while(!s_prime.isTerminal() && steps < 1000000);

        episode.refreshDiscounted(gamma);
		return episode;
	}
}
