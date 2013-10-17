package state;

import action.JointAction;
import agent.Agent;
import agent.AgentsCollection;
import agent.Predator;
import agent.Prey;
import util.Coordinates;
import util.Util;

import java.util.ArrayList;

public class MinimalState extends State {
    final Agent subject;                    /* Represents the Agent to whom this State is subjective. */
    final Prey prey;
    final ArrayList<Predator> predators;
    ArrayList<Coordinates> distances;       /* The Manhattan distances between the subject and all the others. */


//    /**
//     * Random minimal state constructor
//     */
//    public MinimalState(AgentsCollection ac) {
//        this.subject =
//        this.predatorsCoordinates = new ArrayList<>();
//        this.preyCoordinates = new Coordinates(5, 5);
//        for (int i = 0; i < predators; i++) {
//            this.predatorsCoordinates.add(new Coordinates());
//        }
//    }

    public MinimalState(Agent subject, AgentsCollection ac) {
        this.subject = subject;
        this.prey = ac.preys.get(0);
        this.predators = ac.predators;

        this.calculateDistances();
    }

    /**
     * Returns a new state that will occur after a joint action is taken.
     * @param ja the JointAction to be taken.
     */
    public MinimalState nextState(JointAction ja) {
        // 'move' prey
        ArrayList<Coordinates> predatorsCoordinates = new ArrayList<>();

        Prey newPrey = new Prey(this.prey); // copy prey instance

        ArrayList<Predator> newPredators = new ArrayList<>(); // copy predators
        for (Predator p : this.predators) {
            newPredators.add( new Predator(p) );
        }

        newPrey.setCoordinates( newPrey.getCoordinates().createShifted(ja.preyAction));

        for (Predator p : newPredators) {
            // move all predators in the opposite direction.
            p.setCoordinates( newPrey.getCoordinates().createShifted(ja.get(prey)));
            // move predators according to their original actions.
            p.setCoordinates( newPrey.getCoordinates().createShifted(ja.get(p)) );
        }

        return new MinimalState( this.subject, new AgentsCollection(newPrey, newPredators) );
    }

    private void calculateDistances() {
        // Initial calculation of distances:

        ArrayList<Coordinates> distances = new ArrayList<>();

        for (Agent predator: predators) {
            if (predator != subject) {
                distances.add(calculateDistance(predator));
            }
        }

        // We finally add the prey:
        if (this.subject instanceof Predator) {
            distances.add(0, calculateDistance(prey));
        }

        this.distances = distances;
    }

    private Coordinates calculateDistance(Agent agent) {
        int distX, distY;

        int x = subject.getCoordinates().getX() - agent.getCoordinates().getX();
        int xx;

        if (x < 0) { xx = x + Util.DIM; }
        else { xx = x - Util.DIM; }

        if (Math.abs(x) < Math.abs(xx)) { distX = x; }
        else { distX = xx; }

        int innerY = subject.getCoordinates().getY() - agent.getCoordinates().getY();
        int outterY;

        if (innerY < 0) { outterY = innerY + Util.DIM; }
        else { outterY = innerY - Util.DIM; }

        if (Math.abs(innerY) < Math.abs(outterY)) { distY = innerY; }
        else { distY = outterY; }
        return new Coordinates(distX, distY);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (!(obj instanceof State)) { return false; }
        MinimalState other = (MinimalState) obj;

        if (this.distances.size() == other.distances.size()) {
            for (int i = 0; i < distances.size(); i++) {
                if (!this.distances.get(i).equals(other.distances.get(i))) {
                    return false;
                }
            } return true;
        } return false;
    }

    @Override
    public int hashCode() {
        String hashString = "1" + this.prey.getCoordinates().getX() + this.prey.getCoordinates().getY();
        for (Predator predator : this.predators) {
            if (predator.getCoordinates().getX() == 10 && predator.getCoordinates().getY() == 10) {
                hashString += "" + 100;
            } else {
                hashString += predator.getCoordinates().getX() +""+ predator.getCoordinates().getY();
            }
        }
        return Integer.parseInt(hashString);
    }
}
