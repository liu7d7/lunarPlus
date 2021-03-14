package me.ethius.lunarplus.gui.themes.impl;

import me.ethius.lunarplus.gui.Button;
import me.ethius.lunarplus.gui.Window;
import me.ethius.lunarplus.modifications.Mod;
import me.ethius.lunarplus.modifications.ModManager;
import me.ethius.lunarplus.modifications.render.Theme;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class ThemeBase {

    protected static Minecraft mc = Minecraft.getMinecraft();

    public static List<ThemeBase> themes = new ArrayList<ThemeBase>();

    private final String name;

    public ThemeBase(String name) {
        this.name = name;
    }

    public static void initThemes() {
        themes.add(new Watercolor());
    }

    public static ThemeBase getTheme() {
        if (((Theme) ModManager.getMod(Theme.class)).theme.getValString().equals("watercolor")) {
            return themes.get(0);
        } 
        return themes.get(0);

    }

    public void drawTitle(Mod.Category category, Window parent) {

    }

    public void drawButton(String text, Window window, Button button, boolean toggled) {

    }


}
