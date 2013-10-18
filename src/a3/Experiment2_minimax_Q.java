package a3;

import policy.QDeterministicPolicy;
import agent.Agent;
import agent.AgentsCollection;
import algorithms.MiniMaxQLearning;
import algorithms.QLearning;
import episode.EpisodeGenerator;
import state.ReducedState;
import util.Util;

/**
 * In this experiment we will implement independent Q-Learning algorithm and apply it to a multi-agent system.
 */
public class Experiment2_minimax_Q {

    public static void main(String[] args) {
    	Experiment2_minimax_Q.debug();
    	Experiment2_minimax_Q.measurements();
    }
    
    private static void debug() {
    	int predatorCount = 1;
    	int episodeCount = Util.EPISODE_COUNT;
    	double gamma = 0.5;
    	double alpha = 0.1;
    	double decay = 0.5;
        AgentsCollection agents = Builder.experiment2MiniMaxQConfig(Util.INITIAL_MINIMAXQ_Q_VALUE);
    	MiniMaxQLearning.run(agents, gamma, decay, episodeCount);
	}

	public static void measurements() {
//      for (int predatorCount = 1; predatorCount < 5; predatorCount++) {
//      for(int episodeCount = 50; episodeCount < Util.EPISODE_COUNT; episodeCount += 50) {
//          // 1. generate environment
//          AgentsCollection agents = Builder.experiment2Config(predatorCount, Util.INITIAL_Q_VALUE);
//          ReducedState simulationInitialState = new ReducedState(agents.getPredatorsCoordinates());
//
//          // 2. train
//          QLearning.run(agents, Util.ALPHA, Util.GAMMA, episodeCount);
//          System.out.println("%trained.");
//
//          // 3. simulate & output results
//          Util.PREY_WINS = 0;
//          Util.PREDATORS_WIN = 0;
//          EpisodeGenerator simulator = new EpisodeGenerator(agents);
//          double averageEpisodeSize = simulator.getAverageEpisodeSize(simulationInitialState, Util.NUMBER_OF_TEST_RUNS);
////          System.out.println(episodeCount + ", " + averageEpisodeSize);
//          System.out.println(predatorCount+", "+episodeCount+", "+averageEpisodeSize+", "+Util.PREY_WINS+", "+Util.PREDATORS_WIN);
//
//
//  System.out.println("episodeCount, averageRounds");
//  for(int episodeCount = 50; episodeCount < Util.EPISODE_COUNT; episodeCount += 50) {
//
//  	AgentsCollection agents = Builder.experiment2Config(predatorCount, Util.INITIAL_Q_VALUE);
//
//  	// 1. train
//      QLearning.run(agents, Util.ALPHA, Util.GAMMA, episodeCount);
//      System.out.println("%trained.");
//      
//      // after the Q has been learned, 
//      // we apply a Q-based deterministic policy to the agents
//      for(Agent agent : agents) {
//      	QDeterministicPolicy pi = new QDeterministicPolicy();
//      	pi.setQ(agent.q);
//      	agent.setPolicy(pi);
//      }
//      
//      EpisodeGenerator simulator = new EpisodeGenerator(agents);
//      ReducedState simulationInitialState = new ReducedState(agents.getPredatorsCoordinates());
//
//      // 2. simulate & output results
//      double averageRounds = 0.0; //algos.getSimulationAverageRounds(simulations);
//      for (int i = 0; i < Util.NUMBER_OF_TEST_RUNS; i++) {
//          Episode episode = simulator.generate(simulationInitialState, Util.GAMMA);
//          averageRounds += episode.size();
//          System.out.println(episode.size());
//      }
//  }

    }
}
