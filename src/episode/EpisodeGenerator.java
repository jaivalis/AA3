package episode;

import action.JointAction;
import agent.AgentsCollection;
import state.ReducedState;

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
        ReducedState s_prime;

        int steps = 0;
//        System.out.println("step #"+steps + " " + s);
        do {
        	steps++;
            JointAction ja = new JointAction(s, this.agents);

            s_prime = s.nextState(ja);  // s <- s.getSuccessor()

            double preyReward = s_prime.getPreyReward();
            double predReward = s_prime.getPredatorReward();
            episode.addStep(s, ja, preyReward, predReward, s_prime);
            s = s_prime;
//            System.out.println("step #"+steps + " " + s);
            if(steps == 1000){
            	System.out.println(steps+"crap.");
            }
        } while(!s_prime.isTerminal() && steps < 1000000);

        episode.refreshDiscounted(gamma);
		return episode;
	}

    /**
     * Runs numberOfTestRuns simulations and returns the average size (in terms of rounds per episode) for the agent
     * collection and starting from the initialState.
     * @param initialState The starting state for the simulation.
     * @param numberOfTestRuns Count of runs to be tested on.
     * @return The average size (in terms of rounds per episode) for the agent collection.
     */
    public double getAverageEpisodeSize(ReducedState initialState, int numberOfTestRuns) {
        ReducedState s = initialState;
        ReducedState s_prime;

        int steps = 0;
        for (int i = 0; i < numberOfTestRuns; i++) { // run numberOfTestRuns episodes

            do {
                steps++;
                JointAction ja = new JointAction(s, this.agents);

                s_prime = s.nextState(ja);  // s <- s.getSuccessor()

                s = s_prime;
            } while(!s_prime.isTerminal() && steps < 1000000);

        }
        return (double) steps / numberOfTestRuns;
    }
}
