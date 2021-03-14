package me.ethius.lunarplus.gui;

import me.ethius.lunarplus.RenderUtil;
import me.ethius.lunarplus.gui.themes.impl.ThemeBase;
import me.ethius.lunarplus.modifications.Mod;
import me.ethius.lunarplus.modifications.ModManager;
import me.ethius.lunarplus.modifications.render.Theme;
import me.ethius.lunarplus.setting.Setting;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class Window {

    public List<Button> buttons = new ArrayList<>();

    Minecraft mc = Minecraft.getMinecraft();

    public int x, y;
    public Mod.Category category;
    public int arrayCount;
    public boolean dragging;
    public ActualScreen parent;
    public int dragX, dragY;

    public Window(int x, int y, Mod.Category category, ActualScreen parent) {
        this.x = x;
        this.y = y;
        this.category = category;
        this.parent = parent;
    }

    public void render(int mouseX, int mouseY) {
        buttons.clear();
        arrayCount = 0;
        ThemeBase.getTheme().drawTitle(this.category, this);
        arrayCount++;
        for(Mod mod : ModManager.getModuleList(this.category)) {
            buttons.add(new Button(mod, x, y, arrayCount, ButtonType.mod, this, mod.toggled));
            if (mod.isOpened()) {
                if (mod.hasSettings()) {
                    for (Setting s : mod.getSettings()) {
                        arrayCount++;
                        if(s.isCheck()) {
                            buttons.add(new Button(s, x, y, arrayCount, ButtonType.bool, this, s.getValBoolean()));
                        }
                        if (s.isCombo()) {
                            buttons.add(new Button(s, x, y, arrayCount, ButtonType.string, this, false));
                        }
                        if (s.isSlider()) {
                            buttons.add(new Button(s, x, y, arrayCount, ButtonType.doublePrecision, this, false));
                        }
                    }
                }
                arrayCount++;
                buttons.add(new Button(mod, x, y, arrayCount, ButtonType.bind, this, false));
            }
            arrayCount++;
        }
        for (Button b : buttons) {
            b.render();
        }
        if (((Theme) ModManager.getMod(Theme.class)).theme.getValString().equals("watercolor")) {
            boolean haiku = false;
            RenderUtil.drawRect(this.x, this.y + 11, this.x + 85 - 40 - 5 + 9, this.y + 12, 0xff7db1fb);
            RenderUtil.drawRect(this.x + 85 - 40 - 5 + 9, this.y + 11, this.x + 85, this.y + 12, 0xff7db1fb);
        }
        if (this.dragging) {
            this.x = this.dragX + mouseX;
            this.y = this.dragY + mouseY;
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        for (Button b : buttons) {
            b.mouseClicked(mouseX, mouseY, button);
        }
        this.dragX = (int) (this.x - mouseX);
        this.dragY = (int) (this.y - mouseY);
    }

    public void keyTyped(int key) {
        for (Button b : buttons) {
            b.keyTyped(key);
        }
    }

    public boolean withinTitle(int mouseX, int mouseY) {
        return mouseX > x && mouseX < x + 85 && mouseY > y && mouseY < y + 11;
    }

}
