package me.ethius.lunarplus.gui;

import me.ethius.lunarplus.MathUtil;
import me.ethius.lunarplus.gui.themes.impl.ThemeBase;
import me.ethius.lunarplus.modifications.Mod;
import me.ethius.lunarplus.setting.Setting;
import org.lwjgl.input.Keyboard;

public class Button {

    public Mod module;
    public int baseX, baseY, arrayCount;
    public ButtonType type;
    public Setting setting;
    public Window parent;
    public boolean toggled;

    public Button(Mod module, int baseX, int baseY, int arrayCount, ButtonType type, Window parent, boolean moduleToggled) {
        this.module = module;
        this.baseX = baseX;
        this.baseY = baseY;
        this.type = type;
        this.arrayCount = arrayCount;
        this.parent = parent;
        this.toggled = moduleToggled;
    }

    public Button(Setting setting, int baseX, int baseY, int arrayCount, ButtonType type, Window parent, boolean moduleToggled) {
        this.setting = setting;
        this.baseX = baseX;
        this.baseY = baseY;
        this.type = type;
        this.arrayCount = arrayCount;
        this.parent = parent;
        this.toggled = moduleToggled;
    }

    public void render() {
        if (this.type == ButtonType.bind) {
            ThemeBase.getTheme().drawButton("bind: " + ((module.keybind < 0) ? "none" : Keyboard.getKeyName(module.keybind).toLowerCase()), parent, this, this.toggled);
        }
        if (this.type == ButtonType.mod) {
            ThemeBase.getTheme().drawButton(module.getName(), parent, this, this.toggled);
        }
        if (this.type == ButtonType.bool) {
            ThemeBase.getTheme().drawButton(setting.getName(), parent, this, this.toggled);
        }
        if (this.type == ButtonType.doublePrecision) {
            ThemeBase.getTheme().drawButton(setting.getName() + ": " + MathUtil.round(setting.getValDouble()), parent, this, this.toggled);
        }
        if (this.type == ButtonType.string) {
            ThemeBase.getTheme().drawButton(setting.getName() + ": " + setting.getValString(), parent, this, this.toggled);
        }
    }

    public boolean isWithin(int mouseX, int mouseY) {
        return mouseX > this.baseX && mouseX < this.baseX + 85 && mouseY > this.baseY + this.arrayCount * 11 && mouseY < this.baseY + (this.arrayCount + 1) * 11;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isWithin(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.handleLMB(mouseX, mouseY);
            }
            if (mouseButton == 1) {
                this.handleRMB();
            }
            if (mouseButton == 2) {
                this.handleMMB();
            }
        }
    }

    public void handleLMB(int mouseX, int mouseY) {
        if (this.type == ButtonType.bind) {
            this.module.binding = !this.module.binding;
        }
        if (this.type == ButtonType.mod) {
            this.module.toggle();
        }
        if (this.type == ButtonType.string) {
            this.setting.setValString(setting.getNextInList(false));
        }
        if (this.type == ButtonType.doublePrecision) {
            this.setting.setValDouble((((double) (mouseX - baseX)) / 85) * (setting.getMax() - setting.getMin()) + setting.getMin());
        }
        if (this.type == ButtonType.bool) {
            this.setting.setValBoolean(!this.setting.getValBoolean());
        }
        if (this.type == ButtonType.doublePrecision) {
            this.setting.setValDouble(this.setting.getValDouble() - this.setting.getInc());
        }
    }

    public void handleRMB() {
        if (this.type == ButtonType.string) {
            this.setting.setValString(setting.getNextInList(true));
        }
        if (this.type == ButtonType.mod) {
            this.module.setOpened(!this.module.isOpened());
        }
        if (this.type == ButtonType.doublePrecision) {
            this.setting.setValDouble(this.setting.getValDouble() + this.setting.getInc());
        }
    }

    public void handleMMB() {
        if (this.type == ButtonType.bind) {
            this.module.keybind = -2;
        }
    }


    public void keyTyped(int key) {
        if (this.type == ButtonType.bind) {
            if (this.module.isBinding()) {
                this.module.keybind = key;
                this.module.setBinding(false);
            }
        }
    }


}
