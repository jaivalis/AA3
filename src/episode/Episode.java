package episode;

import action.JointAction;
import agent.AgentsCollection;
import state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Episode implements Iterable<EpisodeStep> {
	private ArrayList<EpisodeStep> steps = new ArrayList<>();
	AgentsCollection agents = new AgentsCollection();

    public Episode(AgentsCollection agents) {
        this.agents = agents;
    }

	public void addStep(State s, JointAction ja, double preyR, double predR, State s_prime){
		this.addStep(new EpisodeStep(s, ja, preyR, predR, s_prime));
	}

	public void addStep(EpisodeStep step){
		this.steps.add(step);
	}

	@Override
	public Iterator<EpisodeStep> iterator() {
		return this.steps.iterator();
	}

	private ArrayList<EpisodeStep> getReverted(){
		ArrayList<EpisodeStep> reverted = (ArrayList<EpisodeStep>) this.steps.clone();
		Collections.reverse(reverted);
		return reverted;
	}

    /**
     * Returns the size of the episode in terms of episode steps.
     * @return the size of the episode in terms of episode steps.
     */
    public int size() {
        return this.steps.size();
    }
	
	public void refreshDiscounted(double gamma) {
		double prev_discountedPrey = 0.0, prev_discountedPred = 0.0;
		ArrayList<EpisodeStep> reverted = this.getReverted();
		for(EpisodeStep step : reverted) {
            double curr_discountedPrey = step.getPreyR() + gamma * prev_discountedPrey;
            double curr_discountedPred = step.getPredR() + gamma * prev_discountedPred;
            step.setDiscounted(curr_discountedPrey, curr_discountedPred);
            prev_discountedPrey = curr_discountedPrey;
            prev_discountedPred = curr_discountedPred;
		}
	}

//	public Episode getTauized(Policy pi) {
//		Episode tauized = new Episode(this.prey, this.predC);
//		ArrayList<EpisodeStep> reverted = this.getReverted();
//		ArrayList<EpisodeStep> tail = new ArrayList<EpisodeStep>();
//		for(EpisodeStep step : reverted) {
//			State s = step.getS();
//			action a = step.getA();
//			if(!pi.getAction(s).equals(a)){
//				break;
//			}
//		}
//		Collections.reverse(tail);
//		for(EpisodeStep step : tail){
//			tauized.addStep(step);
//		}
//		return tauized;
//	}
}
