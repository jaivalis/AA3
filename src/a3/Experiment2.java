package a3;

import policy.Q;
import policy.QEpsilonGreedyPolicy;
import util.Coordinates;
import util.Util;
import agent.Agent;
import agent.AgentsCollection;
import agent.Predator;
import agent.Prey;
import algorithms.QLearning;

/**
 * In this experiment we will implement the minimax Q-Learning algorithm and apply it to a 1v1 scenario.
 */
public class Experiment2 {

    public static void main(String[] args) {
        QLearning ql = new QLearning();
        double initialQ = Util.INITIAL_Q_VALUE;
        double alpha = 0.1;
        double gamma = 0.5;
        int episodeCount = 10;
        AgentsCollection agents = new AgentsCollection();
        Predator predator1 = new Predator(new Coordinates(10,0));
        QEpsilonGreedyPolicy pi1 = new QEpsilonGreedyPolicy();
        pi1.setQ(new Q(initialQ));
        predator1.setPolicy(pi1);
        agents.predators.add(predator1);
        Predator predator2 = new Predator(new Coordinates(10,10));
        QEpsilonGreedyPolicy pi2 = new QEpsilonGreedyPolicy();
        pi2.setQ(new Q(initialQ));
        predator2.setPolicy(pi2);
        agents.predators.add(predator2);
        Predator predator3 = new Predator(new Coordinates(0,10));
        QEpsilonGreedyPolicy pi3 = new QEpsilonGreedyPolicy();
        pi3.setQ(new Q(initialQ));
        predator3.setPolicy(pi3);
        agents.predators.add(predator3);
        Prey prey = new Prey(new Coordinates(5,5));
        QEpsilonGreedyPolicy pi4 = new QEpsilonGreedyPolicy();
        pi4.setQ(new Q(initialQ));
        prey.setPolicy(pi4);
        agents.preys.add(prey);
        ql.run(agents , initialQ, alpha, gamma, episodeCount);
    }
}
