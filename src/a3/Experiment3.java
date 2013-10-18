package a3;

import agent.AgentsCollection;
import algorithms.MiniMaxQLearning;
import episode.EpisodeGenerator;
import state.ReducedState;
import util.Util;

/**
 * In this experiment we will implement minimax Q-Learning algorithm and apply it to a 1v1 scenario.
 */
public class Experiment3 {
    public static void main(String[] args) {
        double eta = 0.0;
        double decay = 0.0;


        System.out.println("episodeCount, averageRounds");
        for(int episodeCount = 50; episodeCount < Util.EPISODE_COUNT; episodeCount += 50) {

            AgentsCollection agents = Builder.experiment3Config(Util.INITIAL_Q_VALUE);

            ReducedState simulationInitialState = new ReducedState(agents.getPredatorsCoordinates());

            // 1. train
            MiniMaxQLearning.run(agents, Util.GAMMA, eta, decay, episodeCount);
            System.out.println("%trained.");

            // 2. simulate & output results
            EpisodeGenerator simulator = new EpisodeGenerator(agents);
            double averageEpisodeSize = simulator.getAverageEpisodeSize(simulationInitialState, Util.NUMBER_OF_TEST_RUNS);
            System.out.println(episodeCount + ", " + averageEpisodeSize);
        }
    }
}
