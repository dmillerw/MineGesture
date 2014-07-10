package dmillerw.gesture.gesture;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmillerw
 */
public enum Direction {

	NORTH("N"),
	EAST("E"),
	SOUTH("S"),
	WEST("W"),
	UNKNOWN("U");

	private final String identifier;

	private Direction(String identifier) {
		this.identifier = identifier;
	}

	public static Direction fromAngle(double angle) {
		if (angle >= -45 && angle <= 45) {
			return NORTH;
		} else if (angle >= 45 && angle <= 135) {
			return EAST;
		} else if (angle >= 135 || angle <= -135) {
			return SOUTH;
		} else if (angle >= -135 && angle <= -45) {
			return WEST;
		} else {
			return UNKNOWN;
		}
	}

	public static Direction[] condense(List<Direction> list) {
		List<Direction> temp = new ArrayList<Direction>();
		Direction last = UNKNOWN;

		for (Direction direction : list) {
			if (last != direction && direction != UNKNOWN) {
				temp.add(direction);
				last = direction;
			}
		}

		return temp.toArray(new Direction[temp.size()]);
	}

	public static String toString(Direction ... gesture) {
		StringBuilder sb = new StringBuilder();
		for (Direction direction : gesture) {
			sb.append(direction.identifier);
		}
		return sb.toString();
	}
}
