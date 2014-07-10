package dmillerw.gesture.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmillerw.gesture.handler.InputEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * @author dmillerw
 */
public class RenderHandler {

	@SubscribeEvent
	public void onGameOverlayRender(RenderGameOverlayEvent.Post event) {
		Minecraft mc = Minecraft.getMinecraft();
		Tessellator tessellator = Tessellator.instance;
		ScaledResolution resolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		if (InputEventHandler.detectingGesture && !InputEventHandler.gestureVectors.isEmpty()) {
			tessellator.startDrawing(3);
			for (Vec3 vec3 : InputEventHandler.gestureVectors) {
				tessellator.addVertex(vec3.xCoord / resolution.getScaleFactor(), resolution.getScaledHeight() - vec3.yCoord / resolution.getScaleFactor(), 0);
			}
			tessellator.draw();
		}
	}
}
