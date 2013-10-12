package action;

import java.util.Random;

public class Action {
    public enum action {
        NORTH("N", "^"),
        SOUTH("S", "v"),
        EAST("E", ">"),
        WEST("W", "<"),
        WAIT("X", "-");

        private String shortName;
        private String arrow;

        public String getShortName() {
            return this.shortName;
        }

        public String getArrow() {
            return this.arrow;
        }

        public action getOpposite() {
            switch (this) {
                case WAIT:
                    return action.WAIT;
                case EAST:
                    return action.WEST;
                case WEST:
                    return action.EAST;
                case NORTH:
                    return action.SOUTH;
                case SOUTH:
                    return action.NORTH;
            }
            return action.WAIT;
        }

        public static action getRandom() {
            int rand = (new Random()).nextInt(action.values().length);
            return action.values()[rand];
        }

        private action(String shortName, String arrow) {
            this.shortName = shortName;
            this.arrow = arrow;
        }
    }
}