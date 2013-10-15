package state;

import action.Action;
import action.JointAction;
import util.Coordinates;

import java.util.ArrayList;

/**
 * In this implementation of the state space the Preys position is set to (5,5), the only one moving is the predator and
 * when the prey 'moves' the predator will move to the opposite direction instead.
 */
public class ReducedState extends State {

    public ReducedState(ArrayList<Coordinates> pred) {
        this.preyCoordinates = new Coordinates(5, 5);
        this.predatorsCoordinates = pred;
    }

    /**
     * Returns a new state that will occur after a joint action is taken.
     * @param ja the JointAction to be taken.
     */
    public ReducedState nextState(JointAction ja) {
        // 'move' prey
        ArrayList<Coordinates> predatorsCoordinates = new ArrayList<>();
        Action.action preyAction = ja.preyAction;
        for (int i = 0; i < this.predatorsCoordinates.size(); i++) { // move all predators in the opposite direction.
            Coordinates currCoord = this.predatorsCoordinates.get(i);
            Coordinates newCoord = currCoord.createShifted(preyAction.getOpposite());
            predatorsCoordinates.add(newCoord);
        }

        for (int i = 0; i < this.predatorsCoordinates.size(); i++) { // move predators according to their actions.
            Coordinates currCoord = predatorsCoordinates.get(i);
            Coordinates newCoord = currCoord.createShifted(ja.predatorActions.get(i));
            predatorsCoordinates.set(i, newCoord);
        }
        return new ReducedState(predatorsCoordinates);
    }

    /**
     * Random state constructor
     */
    public ReducedState(int predators) {
        this.predatorsCoordinates = new ArrayList<>();
        this.preyCoordinates = new Coordinates(5, 5);
        for (int i = 0; i < predators; i++) {
            this.predatorsCoordinates.add(new Coordinates());
        }
    }
}
