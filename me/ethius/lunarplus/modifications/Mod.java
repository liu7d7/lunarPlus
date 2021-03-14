package me.ethius.lunarplus.modifications;

import me.ethius.lunarplus.LunarPlus;
import me.ethius.lunarplus.setting.Setting;
import net.minecraft.client.Minecraft;

import java.util.List;

public class Mod {

    public static final int KEY_UNBOUND = -2;

    protected static Minecraft mc = Minecraft.getMinecraft();

    public String name;
    public int keybind;
    public Category category;
    public boolean toggled;
    public boolean binding = false;
    public boolean opened = false;
    public List<Setting> settings;

    public Mod(String name, int defaultKey, Category category) {
        this.name = name;
        this.keybind = defaultKey;
        this.category = category;
        this.toggled = false;
    }

    public boolean nullCheck() {
        return mc.thePlayer == null || mc.theWorld == null;
    }

    public boolean hasSettings() {
        return settings != null;
    }

    public enum Category {
        render
    }

    public void onEnable() {
        LunarPlus.getInstance().getEventManager().register(this);
        setup();
    }

    public void onDisable() {
        LunarPlus.getInstance().getEventManager().unregister(this);
    }

    public void onToggle() {

    }

    public void toggle() {
        toggled = !toggled;
        onToggle();
        if (toggled) {
            onEnable();
        }
        else {
            onDisable();
        }
        LunarPlus.getInstance().getSaveConfig().saveModules();
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setup() {

    }

    public void toggleNoSave()
    {
        this.toggled = !toggled;
        if (toggled) onEnable();
        else onDisable();
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        onToggle();
        if (toggled) {
            onEnable();
        }
        else {
            onDisable();
        }
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isBinding() {
        return binding;
    }

    public void setBinding(boolean binding) {
        this.binding = binding;
    }

    public void setBind(String bindString) {
        int bindNum = Integer.parseInt(bindString);
        this.keybind = bindNum;
    }

    public void setBind(int key) {
        this.keybind = key;
    }


}
