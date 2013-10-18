package a3;

import agent.AgentsCollection;
import algorithms.GIGAWoLF;
import algorithms.MiniMaxQLearning;
import episode.EpisodeGenerator;
import state.ReducedState;
import util.Util;

public class Experiment4 {

    /**
     * In this experiment we will implement minimax Q-Learning algorithm and apply it to a 1v1 scenario.
     */
    public static void main(String[] args) {
        double eta = 0.5;
        double decay = 0.5;

        int predatorCount = 3;
//        for (int predatorCount = 1; predatorCount < 5; predatorCount++) {
            // 1. generate environment
            AgentsCollection agents = Builder.experiment4Config(predatorCount, Util.INITIAL_Q_VALUE);
            ReducedState simulationInitialState = new ReducedState(agents.getPredatorsCoordinates());

            // 2. train + simulate & output results
            GIGAWoLF.run(agents, Util.GAMMA, eta, decay);
            System.out.println("%trained.");
//        }
    }
}