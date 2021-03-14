package me.ethius.lunarplus.modifications.render;

import me.ethius.lunarplus.gui.ActualScreen;
import me.ethius.lunarplus.modifications.Mod;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Mod {

    public ClickGUI() {
        super("click-gui", Keyboard.KEY_RSHIFT, Category.render);
    }

    @Override
    public void onEnable() {
        ActualScreen actualScreen = new ActualScreen();
        actualScreen.init();
        mc.displayGuiScreen(actualScreen);
        setToggled(false);
    }
}
