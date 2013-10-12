package util;

/**
 * Static variables referenced in all parts of the code.
 */
public class Util {
    /* Task 1 */
	public final static int DIM = 11;               /* Dimension of the grid world. */
	public final static double PREYREWARD = 10.0;   /* Reward assigned to the Prey actor. */
    public static final int EPISODE_COUNT = 1000;
    /* Task 2 */
	public static double epsilon = 0.1;             /* Used in the Epsilon-Greedy implementation. */
    public static double tau = 4;             /* Softmax action selection temperature */

    public final static double MAX_ROUNDS = 10000;

//    enum action {
//        NORTH("N", "^"),
//        SOUTH("S", "v"),
//        EAST("E", ">"),
//        WEST("W", "<"),
//        WAIT("X", "-");
//
//        private String shortName;
//        private String arrow;
//
//        public String getShortName() {
//            return this.shortName;
//        }
//
//        public String getArrow() {
//            return this.arrow;
//        }
//
//        public action getOpposite() {
//            switch (this) {
//                case WAIT:
//                    return action.WAIT;
//                case EAST:
//                    return action.WEST;
//                case WEST:
//                    return action.EAST;
//                case NORTH:
//                    return action.SOUTH;
//                case SOUTH:
//                    return action.NORTH;
//            }
//            return action.WAIT;
//        }
//
//        public static Util.Util.action getRandom() {
//            int rand = (new Random()).nextInt(action.values().length);
//            return action.values()[rand];
//        }
//
//        private action(String shortName, String arrow) {
//            this.shortName = shortName;
//            this.arrow = arrow;
//        }
//    };
}
