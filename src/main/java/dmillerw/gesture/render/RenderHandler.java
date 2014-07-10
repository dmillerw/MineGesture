package dmillerw.gesture.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmillerw.gesture.MineGesture;
import dmillerw.gesture.handler.InputEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class RenderHandler {

	@SubscribeEvent
	public void onGameOverlayRender(RenderGameOverlayEvent.Post event) {
		Minecraft mc = Minecraft.getMinecraft();
		Tessellator tessellator = Tessellator.instance;
		ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		if (InputEventHandler.detectingGesture && !InputEventHandler.gestureVectors.isEmpty()) {
			tessellator.startDrawing(3);
			GL11.glLineWidth(MineGesture.lineWidth);
			GL11.glColor3f((float)MineGesture.lineColorR / 255F, (float)MineGesture.lineColorG / 255F, (float)MineGesture.lineColorB / 255F);
			for (Vec3 vec3 : InputEventHandler.gestureVectors) {
				tessellator.addVertex(vec3.xCoord / resolution.getScaleFactor(), resolution.getScaledHeight() - vec3.yCoord / resolution.getScaleFactor(), 0);
			}
			tessellator.draw();
		}
	}
}
