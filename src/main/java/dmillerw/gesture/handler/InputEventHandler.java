package dmillerw.gesture.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import dmillerw.gesture.gesture.Direction;
import dmillerw.gesture.gesture.GestureRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Vec3;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmillerw
 */
public class InputEventHandler {

	public static List<Vec3> gestureVectors = new ArrayList<Vec3>();
	public static List<Direction> gestureDirections = new ArrayList<Direction>();

	public static boolean detectingGesture = false;

	@SubscribeEvent
	public void onMouseEvent(InputEvent.MouseInputEvent event) {
		if (Mouse.isButtonDown(2)) {
			grabMouse(false);
			InputEventHandler.detectingGesture = true;
		} else {
			if (detectingGesture) {
				Direction[] condensed = Direction.condense(gestureDirections);
				int key = GestureRegistry.get(condensed);

				if (key != -1) {
					KeyboardHandler.INSTANCE.fireKey(key);
				}
			}

			grabMouse(true);
			InputEventHandler.detectingGesture = false;
		}

		if (InputEventHandler.detectingGesture) {
			Vec3 current = Vec3.createVectorHelper(Mouse.getX(), Mouse.getY(), 0);
			if (InputEventHandler.gestureVectors.size() > 1) {
				Vec3 last = gestureVectors.get(gestureVectors.size() - 1);
				Vec3 direction = last.subtract(current);

				if (Math.abs(direction.xCoord) >= 10 || Math.abs(direction.yCoord) >= 10) {
					double angle = Math.toDegrees(Math.atan2(direction.xCoord, direction.yCoord));
					gestureDirections.add(Direction.fromAngle(angle));
				}
			}
			InputEventHandler.gestureVectors.add(current);
		} else {
			InputEventHandler.gestureVectors.clear();
			InputEventHandler.gestureDirections.clear();
		}
	}

	private void grabMouse(boolean grab) {
		if (grab != Mouse.isGrabbed()) {
			Mouse.setGrabbed(grab);
			Mouse.setCursorPosition(Minecraft.getMinecraft().displayWidth / 2, Minecraft.getMinecraft().displayHeight / 2);
			Minecraft.getMinecraft().inGameHasFocus = grab;
		}
	}
}
