package a3;

import agent.AgentsCollection;
import algorithms.QLearning;
import util.Util;

/**
 * In this experiment we will implement independent Q-Learning algorithm and apply multi-agent system.
 */
public class Experiment2 {

    public static void main(String[] args) {
        int predatorCount = 2;

        AgentsCollection agents = Builder.experiment2Config(predatorCount, Util.INITIAL_Q_VALUE);

        QLearning.run(agents, Util.ALPHA, Util.GAMMA, Util.EPISODE_COUNT);
    }
}
