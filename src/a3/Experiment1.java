package a3;

import episode.EpisodeGenerator;
import state.ReducedState;

public class Experiment1 {
    static int NUMBER_OF_PREDATORS = 2;

    public static void main(String[] args) {

        EpisodeGenerator eg = new EpisodeGenerator(NUMBER_OF_PREDATORS);
        eg.generate(new ReducedState(NUMBER_OF_PREDATORS), 0.0);
    }
}
