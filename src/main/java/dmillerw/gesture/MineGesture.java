package dmillerw.gesture;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dmillerw.gesture.gesture.Direction;
import dmillerw.gesture.gesture.GestureRegistry;
import dmillerw.gesture.handler.InputEventHandler;
import dmillerw.gesture.handler.KeyboardHandler;
import dmillerw.gesture.render.RenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

/**
 * @author dmillerw
 */
@Mod(modid = "MineGesture", name = "MineGesture", version = "%MOD_VERSION", dependencies = "required-after:Forge@[%FORGE_VERSION%,)")
public class MineGesture {

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(new InputEventHandler());
		FMLCommonHandler.instance().bus().register(KeyboardHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(new RenderHandler());

		GestureRegistry.register(Minecraft.getMinecraft().gameSettings.keyBindJump, Direction.NORTH);
		GestureRegistry.register(Minecraft.getMinecraft().gameSettings.keyBindDrop, Direction.SOUTH);
		GestureRegistry.register(Minecraft.getMinecraft().gameSettings.keyBindInventory, Direction.WEST, Direction.SOUTH, Direction.EAST);

		// BAUBLES TEST
		GestureRegistry.register(Keyboard.KEY_B, Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST, Direction.EAST, Direction.NORTH, Direction.WEST);
	}
}
