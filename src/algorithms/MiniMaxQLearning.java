package algorithms;

import java.util.HashMap;
import java.util.Random;

import policy.LocalEpsilonPolicy;
import policy.Policy;
import policy.Q;
import policy.QEpsilonGreedyPolicy;
import policy.V;
import action.Action.action;
import action.JointAction;
import agent.Agent;
import agent.AgentsCollection;
import agent.Predator;
import agent.Prey;
import state.ReducedState;
import util.Util;

public class MiniMaxQLearning {

    /**
     * In this case agents contains only 1 agent & 1 prey.
     * @param agents Should contain 1 Predator & 1 prey.
     * @param gamma Decay factor.
     * @param eta Learning rate.
     * @param decay
     */
    public static void run(AgentsCollection agents, double gamma, double decay, int episodeCount) {
        double[][] qMatrix = new double[5][5];
        Prey prey = agents.preys.get(0);
        Predator predator = agents.predators.get(0);
        double alpha = 1.0;
        
        for (int i = 0; i < episodeCount; i++) {
        	V preyV = new V();
        	V predatorV = new V();
            ReducedState s = new ReducedState(agents.predators.size()); // Random state

            while ( !s.isTerminal() ) {
                ReducedState prevS = s;

                JointAction ja = new JointAction(s, agents);
                Random rand = new Random();
                if( rand.nextDouble() < Util.MINIMAXQ_EXPLOR){
                	ja.preyAction = action.getRandom();
                	ja.predatorActions.put(0, action.getRandom() );
                }
                
                ReducedState s_prime = prevS.nextState(ja);
                double preyR = s_prime.getPreyReward();
                double predatorR = s_prime.getPreyReward();
                MiniMaxQLearning.learn(prey, s, ja.preyAction, ja.predatorActions.get(0), preyR, s_prime, preyV, alpha, gamma);
                MiniMaxQLearning.learn(prey, s, ja.predatorActions.get(0), ja.preyAction, predatorR, s_prime, predatorV, alpha, gamma);
                alpha = alpha * decay;
            }
        }
    }
    
    public static void learn(Agent agent, ReducedState s, action a, action o, double rew, ReducedState s_prime, V v, double alpha, double gamma) {
    	// extracting objects that will be useful later
    	Q q = agent.getQ();
    	
    	// FIXME: assuming agents are configured with LocalEpsilonPolicy instead of other policies
    	LocalEpsilonPolicy pi = (LocalEpsilonPolicy)agent.getPolicy();
    	
    	// setting Q[s,a,o]

    	Double v_s_prime = v.get(s_prime);
    	if(v_s_prime == null) {// lazy value filling
    		v_s_prime = 1.0;
    		v.put(s_prime, v_s_prime);
    	}
    	
    	double q_sao = (1-alpha) * q.get(s_prime, a, o) + alpha * (rew + gamma * v_s_prime);
    	q.set(s, a, o, q_sao);
    	
		// finding pi[s,.] , not with linear programming, but even in this
    	// non-optimized with an epsilon policy case it's working and that's what counts

    	// calculating argmax{pi'[s,.]
    	double max_epsilon = 1.0/(action.values().length);
    	double epsilon_increment = max_epsilon/10;
    	
    	double max = Double.NEGATIVE_INFINITY;
    	double argmax_epsilon = 0.0;
    	action argmax_action = null;
    	
    	// search for the best policy for the current state
    	for(action designated_action : action.values()) {
    		for(double epsilon = 0.0; epsilon < max_epsilon; epsilon += epsilon_increment) {
    			pi.setEpsilonAction(s, epsilon, designated_action);

		    	// calculating min{o'
		    	double min = Double.POSITIVE_INFINITY;
		    	for(action o_prime : action.values()) {
			    	// calculating sum{a'
			    	double sum = 0.0;
			    	for(action a_prime : action.values()){ 
			    		sum += pi.getActionProbability(s, a_prime) * q.get(s_prime, a_prime, o_prime);
			    	}
			    	if(sum < min) {
			    		min = sum;
			    	}
		    	}
		    	
		    	if(max < min) {
		    		max = min;
		    		argmax_epsilon = epsilon;
		    		argmax_action = designated_action;
		    	}
    		}
    	}
    	
    	// configuring policy for current state
    	pi.setEpsilonAction(s, argmax_epsilon, argmax_action);
    	
    	// updating V values
    	double v_min = Double.POSITIVE_INFINITY;
    	for(action o_prime: action.values()) {
    		double sum = 0.0;
    		for(action a_prime : action.values()) {
    			sum += pi.getActionProbability(s, a_prime) * q.get(s_prime, a_prime, o_prime);
    		}
    		if(sum < v_min){
    			v_min = sum;
    		}
    	}
    	
    	v.put(s, v_min);
    }
}
