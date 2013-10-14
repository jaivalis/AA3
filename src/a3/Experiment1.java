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

        int predatorCount = 2;

        AgentsCollection agents = Builder.experiment1Config(predatorCount);

        EpisodeGenerator eg = new EpisodeGenerator(agents);
        eg.generate(new ReducedState(agents.getPredatorsCoordinates()), 0.0);
//        for(int i=0; i < Util.NUMBER_OF_TEST_RUNS; i++) {
//        	eg.generate(new ReducedState(Util.PREDATOR_COUNT), 0.0);
//        }
    }
}
