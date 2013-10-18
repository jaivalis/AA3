	

    package a3;
     
    import agent.AgentsCollection;
    import algorithms.QLearning;
    import episode.EpisodeGenerator;
    import state.ReducedState;
    import util.Util;
     
    /**
     * In this experiment we will implement independent Q-Learning algorithm and apply it to a multi-agent system.
     */
    public class Experiment2 {
     
        public static void main(String[] args) {
    //        int predatorCount = 2;
            System.out.println("predatorCount, episodeCount, averageEpisodeSize, preyWins, predatorsWin");
     
            for (int predatorCount = 1; predatorCount < 5; predatorCount++) {
                for(int episodeCount = 50; episodeCount < Util.EPISODE_COUNT; episodeCount += 50) {
                    // 1. generate environment
                    AgentsCollection agents = Builder.experiment2Config(predatorCount, Util.INITIAL_Q_VALUE);
                    ReducedState simulationInitialState = new ReducedState(agents.getPredatorsCoordinates());
     
                    // 2. train
                    QLearning.run(agents, Util.ALPHA, Util.GAMMA, episodeCount);
                    System.out.println("%trained.");
     
                    // 3. simulate & output results
                    Util.PREY_WINS = 0;
                    Util.PREDATORS_WIN = 0;
                    EpisodeGenerator simulator = new EpisodeGenerator(agents);
                    double averageEpisodeSize = simulator.getAverageEpisodeSize(simulationInitialState, Util.NUMBER_OF_TEST_RUNS);
    //                System.out.println(episodeCount + ", " + averageEpisodeSize);
                    System.out.println(predatorCount+", "+episodeCount+", "+averageEpisodeSize+", "+Util.PREY_WINS+", "+Util.PREDATORS_WIN);
     
                }
            }
        }
    }

