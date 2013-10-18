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
        System.out.println("predatorCount, averageEpisodeSize, preyWins, predatorsWin");

        for (int predatorCount = 1; predatorCount < 5; predatorCount++) {
            // 1. generate environment
            AgentsCollection agents = Builder.experiment1Config(predatorCount);
            EpisodeGenerator eg = new EpisodeGenerator(agents);
            eg.generate(new ReducedState(agents.getPredatorsCoordinates()), 0.0);
            EpisodeGenerator simulator = new EpisodeGenerator(agents);

            // 2. simulate & output results
            Util.PREY_WINS = 0;
            Util.PREDATORS_WIN = 0;
            ReducedState simulationInitialState = new ReducedState(agents.getPredatorsCoordinates());
            double averageEpisodeSize = simulator.getAverageEpisodeSize(simulationInitialState, Util.NUMBER_OF_TEST_RUNS);
            System.out.println(predatorCount + ", " + averageEpisodeSize+ ", " +Util.PREY_WINS+  ", " +Util.PREDATORS_WIN);
        }
    }
}
