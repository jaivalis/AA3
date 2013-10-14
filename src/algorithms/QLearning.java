package algorithms;

import java.util.ArrayList;

import policy.Q;
import policy.QPolicy;
import state.ReducedState;
import state.State;
import util.Util;
import action.Action.action;
import action.JointAction;
import agent.Agent;
import agent.AgentsCollection;

public class QLearning {
    /**
     * Implementation of the Q-Learning algorithm for many agents.
     * @param pi The epsilon-greedy policy that will be gradually updated within the algorithm according to the Q values
     * @param initialQ Initial value for Q.
     * @param alpha Learning rate.
     * @param gamma Decay factor.
     * @return
     */
    public void run(AgentsCollection agents, double initialQ, double alpha, double gamma, int episodeCount) {
        for (int i = 0; i < episodeCount; i++) {  // repeat for each episode
            State s = new ReducedState(Util.PREDATOR_COUNT); // initialize s randomly
            State s_prime;
            do { // repeat for each step of episode
            	JointAction ja = new JointAction(s,agents);

                // Take action a. observe r, s'
            	s_prime = s;
            	s = (State) s.clone();
                s_prime.nextState(ja); // make transition from s to s'

                for(Agent agent : agents){
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
                //System.out.println("a:"+a+" s':"+s_prime); //FIXME DEBUG
            } while (!s.isTerminal()); // repeat until s is terminal
        }
    }

}
