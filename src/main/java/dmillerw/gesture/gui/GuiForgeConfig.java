package dmillerw.gesture.gui;

import cpw.mods.fml.client.config.GuiConfig;
import dmillerw.gesture.MineGesture;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

/**
 * @author dmillerw
 */
public class GuiForgeConfig extends GuiConfig {

	public GuiForgeConfig(GuiScreen parent) {
		super(
			parent,
			new ConfigElement(MineGesture.configuration.getCategory("visual")).getChildElements(),
			"MineGesture",
			false,
			false,
			GuiConfig.getAbridgedConfigPath(MineGesture.configuration.toString())
		);
	}
}
