package me.ethius.lunarplus;

import me.ethius.lunarplus.event.EventManager;
import me.ethius.lunarplus.font.FontManager;
import me.ethius.lunarplus.gui.themes.impl.ThemeBase;
import me.ethius.lunarplus.modifications.ModManager;
import me.ethius.lunarplus.setting.LoadConfig;
import me.ethius.lunarplus.setting.SaveConfig;
import org.lwjgl.opengl.Display;

public class LunarPlus {

    protected EventManager eventManager;

    protected FontManager fm;

    protected static final ModManager modManager = new ModManager();

    protected static LoadConfig loadConfig;
    protected static SaveConfig saveConfig;

    public static String NAME = "lunar+ ";
    public static String VERSION = "b0.1 ";
    protected static LunarPlus instance = new LunarPlus();

    public EventManager getEventManager() {
        return eventManager;
    }

    public LoadConfig getLoadConfig() {
        return loadConfig;
    }
    public SaveConfig getSaveConfig() {
        return saveConfig;
    }


    public FontManager getFontManager() {
        return fm;
    }


    public static LunarPlus getInstance() {
        return instance;
    }

    public void initLunarPlus() {
        eventManager = new EventManager();
        ThemeBase.initThemes();
        System.out.println("Initializing " + NAME + VERSION);
        Display.setTitle(NAME + VERSION);
        fm = new FontManager();
        modManager.init();
        eventManager.register(this);
        loadConfig = new LoadConfig();
        saveConfig = new SaveConfig();
    }

    public void stopClient() {
        eventManager.unregister(this);
        saveConfig.saveModules();
    }

}
