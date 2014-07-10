package dmillerw.gesture.gesture;

import com.google.common.collect.Maps;
import net.minecraft.client.settings.KeyBinding;

import java.util.Map;

/**
 * @author dmillerw
 */
public class GestureRegistry {

	public static Map<String, Integer> gestureMap = Maps.newHashMap();

	public static void register(KeyBinding keyBinding, Direction ... gesture) {
		register(keyBinding.getKeyCode(), gesture);
	}

	public static void register(int key, Direction ... gesture) {
		gestureMap.put(Direction.toString(gesture), key);
	}

	public static int get(Direction ... gesture) {
		for (Map.Entry<String, Integer> entry : gestureMap.entrySet()) {
			if (Direction.toString(gesture).equals(entry.getKey())) {
				return entry.getValue();
			}
		}
		return -1;
	}

	/** Basic recursive implemention of Levenshtein Distance algorithm */
	public static int levenshteinDistance(String s1, String s2, int s1Length, int s2Length) {
		if (s1Length == 0) {
			return s2Length;
		}

		if (s2Length == 0) {
			return s1Length;
		}

		int cost = 1;

		if (s1.charAt(s1Length - 1) == s2.charAt(s2Length - 1)) {
			cost = 0;
		}

		int s1Minus = levenshteinDistance(s1, s2, s1Length - 1, s2Length) + 1;
		int s2Minus = levenshteinDistance(s1, s2, s1Length, s2Length - 1) + 1;
		int sCost = levenshteinDistance(s1, s2, s1Length - 1, s2Length - 1) + cost;

		return Math.min(s1Minus, Math.min(s2Minus, sCost));
	}
}
