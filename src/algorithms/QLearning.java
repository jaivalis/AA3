package algorithms;

import action.Action.action;
import action.JointAction;
import agent.Agent;
import agent.AgentsCollection;
import state.ReducedState;

public class QLearning {
    /**
     * Implementation of the Q-Learning algorithm.
     * @param agents Container for the environment agents
     * @param alpha Learning rate.
     * @param gamma Decay factor.
     * @param episodeCount Count of episodes for which the agents will learn.
     */
    public static void run(AgentsCollection agents, double alpha, double gamma, int episodeCount) {
        for (int i = 0; i < episodeCount; i++) {  // repeat for each episode
            ReducedState s = new ReducedState(agents.predators.size()); // initialize s randomly
            ReducedState s_prime;
            do { // repeat for each step of episode
            	JointAction ja = new JointAction(s, agents);
                // Take action ja
                s_prime = s.nextState(ja);   // make transition from s to s'

                for(Agent agent : agents) {
                	action single_action = ja.actionsMap.get(agent);
                	double q_sa = agent.getQ().get(s, single_action);
                    double max_a_q = agent.getQ().getMax(s_prime);

                    // the reward r that is consequence of action a, in our case (prey/predator system) is always set
                    // as the reward associated with the destination state
                    double r = s_prime.getReward(agent);

                    double discounted_max_a_q = gamma * max_a_q;
                    double newQ_sa = q_sa + alpha * (r + discounted_max_a_q - q_sa);

                    agent.getQ().set(s, single_action, newQ_sa); // Q(s,a) = Q(s,a) + α[r + γmax_aQ() - Q(s,a)]
                }
                s = s_prime;
            } while (!s.isTerminal()); // repeat until s is terminal
        }
    }
}
