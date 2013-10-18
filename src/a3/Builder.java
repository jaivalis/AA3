package a3;

import agent.AgentsCollection;
import agent.Predator;
import agent.Prey;
import policy.*;
import util.Coordinates;

public class Builder {
    static Coordinates[] predatorCoordinates = {new Coordinates(0,0), new Coordinates(10,0),
                                                new Coordinates(0,10), new Coordinates(10,10)};

    /**
     * Configures a Prey and a given number of Predators to random policies.
     * @param predatorCount Number of predators to be created.
     * @return AgentCollection with RandomPolicy Agents.
     */
    public static AgentsCollection experiment1Config(int predatorCount) {
        AgentsCollection ac = new AgentsCollection();

        Prey p = new Prey(new Coordinates(5, 5), new Q(15), new RandomPreyPolicy());
        ac.preys.add(p);

        for (int i = 0; i < predatorCount; i++) {
            Predator pp = new Predator(Builder.predatorCoordinates[i], new Q(15), new RandomPredatorPolicy());
            ac.predators.add(pp);
        }
        return ac;
    }

    /**
     * Configures a Prey and a given number of Predators to QEpsilonGreedyPolicies.
     * @param predatorCount Number of predators to be created.
     * @param initialQ InitialQ to be used for Q value.
     * @return AgentCollection with QEpsilonGreedyPolicy Agents.
     */
    public static AgentsCollection experiment2Config(int predatorCount, double initialQ) {
        AgentsCollection ac = new AgentsCollection();

        Q preyQ = new Q(initialQ);
        QEpsilonGreedyPolicy preyP = new QEpsilonGreedyPolicy();
        preyP.setQ(preyQ);
        Prey p = new Prey(new Coordinates(5, 5), preyQ, preyP);
        ac.preys.add(p);

        for (int i = 0; i < predatorCount; i++) {
            Q predQ = new Q(initialQ);
            QEpsilonGreedyPolicy predP = new QEpsilonGreedyPolicy();
            predP.setQ(predQ);
            Predator pp = new Predator(Builder.predatorCoordinates[i], predQ, predP);
            ac.predators.add(pp);
        }
        return ac;
    }

    /**
     * Configures single Prey-single Predator.
     * @param initialQ InitialQ to be used for Q value.
     * @return AgentCollection with 2 agents in it.
     */
    public static AgentsCollection experiment3Config(double initialQ) {
        AgentsCollection ac = new AgentsCollection();

        // Create prey
        Q preyQ = new Q(initialQ);
        QEpsilonGreedyPolicy preyP = new QEpsilonGreedyPolicy();
        preyP.setQ(preyQ);
        Prey p = new Prey(new Coordinates(5, 5), preyQ, preyP);
        ac.preys.add(p);

        // Create predator
        Q predQ = new Q(initialQ);
        QEpsilonGreedyPolicy predP = new QEpsilonGreedyPolicy();
        predP.setQ(predQ);
        Predator pp = new Predator(Builder.predatorCoordinates[0], predQ, predP);
        ac.predators.add(pp);
        return ac;
    }


    /**
     * Configures multiAgent WoLFPolicy
     * @param predatorCount Number of predators to be created.
     * @param initialQ InitialQ to be used for Q value.
     * @return AgentCollection with 2 agents in it.
     */
    public static AgentsCollection experiment4Config(int predatorCount, double initialQ) {
        AgentsCollection ac = new AgentsCollection();

        // Create prey
        WoLFPolicy preyP = new WoLFPolicy();
        Prey p = new Prey(new Coordinates(5, 5), null, preyP);
        ac.preys.add(p);

        // Create predators
        for (int i = 0; i < predatorCount; i++) {
            WoLFPolicy predP = new WoLFPolicy();
            Predator pp = new Predator(Builder.predatorCoordinates[i], null, predP);
            ac.predators.add(pp);
        }
        return ac;
    }
}
