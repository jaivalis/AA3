package algorithms;

import action.Action.action;
import agent.Agent;
import agent.AgentsCollection;
import agent.StateAction;
import episode.Episode;
import episode.EpisodeGenerator;
import episode.EpisodeStep;
import policy.WoLFPolicy;
import state.ReducedState;
import util.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class GIGAWoLF {

    public static void run(AgentsCollection agents, double gamma, double eta, double decay) {
        for(int episodeCount = 50; episodeCount < Util.EPISODE_COUNT; episodeCount += 50) {

            for (int i = 0; i < episodeCount; i++) {

                HashMap<Agent, ArrayList<Integer>> actions = new HashMap<>();

                for (Agent agent: agents) { actions.put(agent, new ArrayList<Integer>()); }

                ReducedState initialState = new ReducedState(agents.predators.size()); // Random initial State.

                EpisodeGenerator eg = new EpisodeGenerator(agents);
                Episode episode = eg.generate(initialState, gamma);

                // Update rules:
                for (EpisodeStep step : episode) {
                    for (Agent agent : agents) {

                        action a = agent.getAction(step.getS());
                        ReducedState s = (ReducedState) step.getS();
                        StateAction sa = new StateAction(s, a);

                        double reward = step.getReward(agent);

                        if (reward != 0.0) {
                            WoLFPolicy wp = (WoLFPolicy) agent.getPolicy();
                            // Step 1
                            double x_t = wp.getX(sa);
                            double xNextHat = x_t + eta * reward;
//                            wp.setX(sa, xNextHat);
                            wp.normalizeX();

                            // Step 2
                            double z_t = wp.getZ(sa);
                            double zNext = z_t + eta * reward / 3;
                            wp.setZ(sa, xNextHat);
                            wp.normalizeX();

                            // Step 3
                            double delta = Math.min(1, Math.abs(xNextHat - z_t) / Math.abs(zNext - xNextHat));

                            // Step 4
                            double xNext = xNextHat + delta * (zNext - xNextHat);
                            wp.setX(sa, xNext);
                        }
                    }
                } eta *= decay; // reduce eta
            }

            // Simulate & output results.
            Util.PREY_WINS = 0;
            Util.PREDATORS_WIN = 0;
            EpisodeGenerator simulator = new EpisodeGenerator(agents);
            ReducedState simulationInitialState = new ReducedState(agents.getPredatorsCoordinates());
            double averageEpisodeSize = simulator.getAverageEpisodeSize(simulationInitialState, Util.NUMBER_OF_TEST_RUNS);
            System.out.println(episodeCount + ", " + averageEpisodeSize+", "+Util.PREY_WINS+", "+Util.PREDATORS_WIN);
        }

    }
}
