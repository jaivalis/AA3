package algorithms;

import action.JointAction;
import agent.AgentsCollection;
import state.ReducedState;

public class MiniMaxQLearning {

    /**
     * In this case agents contains only 1 agent & 1 prey.
     * @param agents Should contain 1 Predator & 1 prey.
     * @param gamma Decay factor.
     * @param eta Learning rate.
     * @param decay
     */
    public static void run(AgentsCollection agents, double gamma, double eta, double decay, int episodeCount) {
        double[][] qMatrix = new double[5][5];

        double alpha = 1.0;

        for (int i = 0; i < episodeCount; i++) {

            ReducedState s = new ReducedState(agents.predators.size()); // Random state

            while ( !s.isTerminal() ) {
                ReducedState prevS = s;
                JointAction ja = new JointAction(s, agents);
                ReducedState s_prime = prevS.nextState(ja);

//                //TODO need Omicron class
//                // if so not in Q:
//                    //Q.put(sao, 1.0);
//                // if so not in V:
//                   //V.put(sao, 1.0);

//                alpha *= decay;

                s = s_prime;
            }
        }
    }
}