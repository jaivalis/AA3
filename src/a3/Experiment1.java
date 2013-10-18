package a3;

import agent.AgentsCollection;
import episode.EpisodeGenerator;
import state.ReducedState;
import util.Util;

/**
 * In this experiment we will implement independent Q-Learning algorithm and apply it to a multi-agent system.
 */
public class Experiment1 {

    public static void main(String[] args) {
        System.out.println("predatorCount,averageEpisodeSize");

        for (int predatorCount = 1; predatorCount < 5; predatorCount++) {
            AgentsCollection agents = Builder.experiment1Config(predatorCount);
            EpisodeGenerator eg = new EpisodeGenerator(agents);
            eg.generate(new ReducedState(agents.getPredatorsCoordinates()), 0.0);
            EpisodeGenerator simulator = new EpisodeGenerator(agents);

            // simulate and output results
            ReducedState simulationInitialState = new ReducedState(agents.getPredatorsCoordinates());
            double averageEpisodeSize = simulator.getAverageEpisodeSize(simulationInitialState, Util.NUMBER_OF_TEST_RUNS);
            System.out.println(predatorCount + ", " + averageEpisodeSize);
        }
    }
}
