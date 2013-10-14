package action;

import java.util.HashMap;

public class PossibleActionsConcrete extends PossibleActions {
	
	/**
	 * Constructor initializes all action probabilities to 0.2.
	 */
	public PossibleActionsConcrete() { 	
		actionProbability = new HashMap<Action.action, Double>();
		actionProbability.put(Action.action.NORTH, 0.2);
		actionProbability.put(Action.action.SOUTH, 0.2);
		actionProbability.put(Action.action.EAST, 0.2);
		actionProbability.put(Action.action.WEST, 0.2);
		actionProbability.put(Action.action.WAIT, 0.2);
	}
}