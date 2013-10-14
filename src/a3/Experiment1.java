package a3;

import agent.AgentsCollection;
import agent.Predator;
import agent.Prey;
import algorithms.QLearning;
import episode.EpisodeGenerator;
import state.ReducedState;
import util.Coordinates;
import util.Util;

public class Experiment1 {

    public static void main(String[] args) {

        EpisodeGenerator eg = new EpisodeGenerator(Util.PREDATOR_COUNT);
        for(int i=0; i < Util.NUMBER_OF_TEST_RUNS; i++) {
        	// FIXME statistics and such
        	eg.generate(new ReducedState(Util.PREDATOR_COUNT), 0.0);
        }
        
        QLearning ql = new QLearning();
        double initialQ = Util.INITIAL_Q_VALUE;
        double alpha = 0.1;
        double gamma = 0.5;
        int episodeCount = 10;
        AgentsCollection agents = new AgentsCollection();
        agents.predators.add(new Predator(new Coordinates()));
        agents.predators.add(new Predator(new Coordinates()));
        agents.preys.add(new Prey());
		ql.run(agents , initialQ, alpha, gamma, episodeCount);
    }
}
