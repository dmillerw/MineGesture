package dmillerw.gesture;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmillerw.gesture.gesture.Direction;
import dmillerw.gesture.gesture.GestureRegistry;
import dmillerw.gesture.handler.InputEventHandler;
import dmillerw.gesture.handler.KeyboardHandler;
import dmillerw.gesture.render.RenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.lwjgl.input.Keyboard;

/**
 * @author dmillerw
 */
@Mod(modid = "MineGesture", name = "MineGesture", version = "%MOD_VERSION", dependencies = "required-after:Forge@[%FORGE_VERSION%,)", guiFactory = "dmillerw.gesture.gui.MineGestureGuiFactory")
public class MineGesture {

	@Mod.Instance("MineGesture")
	public static MineGesture instance;

	public static Configuration configuration;

	public static int lineWidth = 2;
	public static int lineColorR = 0;
	public static int lineColorG = 0;
	public static int lineColorB = 0;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		if (!event.getSide().isClient()) {
			// NO BAD STOP
			return;
		}

		configuration = new Configuration(event.getSuggestedConfigurationFile());
		configuration.load();

		syncConfig();

		FMLCommonHandler.instance().bus().register(MineGesture.instance);
		FMLCommonHandler.instance().bus().register(new InputEventHandler());
		FMLCommonHandler.instance().bus().register(KeyboardHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(new RenderHandler());

		GestureRegistry.register(Minecraft.getMinecraft().gameSettings.keyBindJump, Direction.NORTH);
		GestureRegistry.register(Minecraft.getMinecraft().gameSettings.keyBindDrop, Direction.SOUTH);
		GestureRegistry.register(Minecraft.getMinecraft().gameSettings.keyBindInventory, Direction.WEST, Direction.SOUTH, Direction.EAST);

		// BAUBLES TEST
		GestureRegistry.register(Keyboard.KEY_B, Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST, Direction.EAST, Direction.NORTH, Direction.WEST);
	}

	public void syncConfig() {
		lineWidth = configuration.get("visual", "lineWidth", 2, "Width of the gesture line").getInt(2);
		lineColorR = configuration.get("visual", "lineColorR", 0, "R component of the gesture line color. 0 - 255").getInt(0);
		lineColorG = configuration.get("visual", "lineColorG", 0, "G component of the gesture line color. 0 - 255").getInt(0);
		lineColorB = configuration.get("visual", "lineColorB", 0, "B component of the gesture line color. 0 - 255").getInt(0);

		if (lineColorR < 0) {
			lineColorR = 0;
		} else if (lineColorR > 255) {
			lineColorR = 255;
		}

		if (lineColorG < 0) {
			lineColorG = 0;
		} else if (lineColorG > 255) {
			lineColorG = 255;
		}

		if (lineColorB < 0) {
			lineColorB = 0;
		} else if (lineColorB > 255) {
			lineColorB = 255;
		}

		if (configuration.hasChanged()) {
			configuration.save();
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals("MineGesture")) {
			syncConfig();
		}
	}
}
