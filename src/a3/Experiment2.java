package a3;

import agent.AgentsCollection;
import algorithms.QLearning;
import episode.Episode;
import episode.EpisodeGenerator;
import state.ReducedState;
import util.Util;

/**
 * In this experiment we will implement independent Q-Learning algorithm and apply multi-agent system.
 */
public class Experiment2 {

    public static void main(String[] args) {
        int predatorCount = 2;

        AgentsCollection agents = Builder.experiment2Config(predatorCount, Util.INITIAL_Q_VALUE);

        ReducedState simulationInitialState = new ReducedState(agents.getPredatorsCoordinates());

        EpisodeGenerator simulator = new EpisodeGenerator(agents);

        System.out.println("episodeCount, averageRounds");
        for(int episodeCount = 50; episodeCount < Util.EPISODE_COUNT; episodeCount += 50) {
            // 1. train
            QLearning.run(agents, Util.ALPHA, Util.GAMMA, episodeCount);
            System.out.println("%trained.");

            // 2. simulate & output results
            double averageRounds = 0.0; //algos.getSimulationAverageRounds(simulations);
            for (int i = 0; i < Util.NUMBER_OF_TEST_RUNS; i++) {
                Episode episode = simulator.generate(simulationInitialState, Util.GAMMA);
                averageRounds += episode.size();
            }
            averageRounds /= Util.NUMBER_OF_TEST_RUNS;
            System.out.println(episodeCount + ", " + averageRounds);
        }
    }
}
