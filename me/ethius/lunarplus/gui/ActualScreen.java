package me.ethius.lunarplus.gui;

import me.ethius.lunarplus.modifications.Mod;
import me.ethius.lunarplus.modifications.ModManager;
import me.ethius.lunarplus.modifications.render.ClickGUI;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActualScreen extends GuiScreen {

    List<Window> windows = new ArrayList<>();

    public void init() {
        windows.add(new Window(50, 50, Mod.Category.render, this));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (Window window : windows) {
            window.mouseClicked(mouseX, mouseY, mouseButton);
            if (window.withinTitle(mouseX, mouseY)) {
                window.dragging = true;
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (Window window : windows) {
            window.render(mouseX, mouseY);
        }
    }

    @Override
    public void onGuiClosed() {
        ModManager.getMod(ClickGUI.class).setToggled(false);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        for (Window window : windows) {
            window.dragging = false;
        }
    }

    @Override
    public void keyTyped(char typed, int keyCode) throws IOException {
        System.out.println(keyCode);
        for (Window w : windows) {
            w.keyTyped(keyCode);
        }
        super.keyTyped(typed, keyCode);
    }

}
