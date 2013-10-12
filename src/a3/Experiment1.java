package a3;

import episode.EpisodeGenerator;
import state.ReducedState;
import util.Util;

public class Experiment1 {

    public static void main(String[] args) {

        EpisodeGenerator eg = new EpisodeGenerator(Util.PREDATOR_COUNT);
        for(int i=0; i < Util.NUMBER_OF_TEST_RUNS; i++) {
        	// FIXME statistics and such
        	eg.generate(new ReducedState(Util.PREDATOR_COUNT), 0.0);
        }
    }
}
