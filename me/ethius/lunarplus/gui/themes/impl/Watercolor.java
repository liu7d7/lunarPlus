package me.ethius.lunarplus.gui.themes.impl;

import me.ethius.lunarplus.LunarPlus;
import me.ethius.lunarplus.RenderUtil;
import me.ethius.lunarplus.font.TTFFontRenderer;
import me.ethius.lunarplus.gui.Button;
import me.ethius.lunarplus.gui.Window;
import me.ethius.lunarplus.modifications.Mod;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class Watercolor extends ThemeBase {

    final ResourceLocation WATERCOLOR_SQUARES = new ResourceLocation("neptune/imgs/watercolor.png");
    final ResourceLocation WATERCOLOR_ICONS = new ResourceLocation("neptune/imgs/watercolorbuttonsshort.png");
    final ResourceLocation FOLDER_ICON = new ResourceLocation("neptune/imgs/foldericon.png");

    public Watercolor() {
        super("Watercolor");
    }

    @Override
    public void drawTitle(Mod.Category category, Window parent) {
        TTFFontRenderer sigmaFont = LunarPlus.getInstance().getFontManager().getFont("SEG 17");
        boolean haiku = false;
        GL11.glPushMatrix();
        RenderUtil.drawRect(parent.x, parent.y + 11, parent.x + 85 - 40 - 4 + 9, parent.y + 12, 0xff7db1fb);
        RenderUtil.drawRect(parent.x + 85 - 40 - 7 + 9, parent.y + 11, parent.x + 85, parent.y + 12, 0xff3573d6);
        RenderUtil.drawRect(parent.x, parent.y, parent.x + 85 - 40 - 4 + 9, parent.y + 11, 0xff5297f9);
        RenderUtil.drawRect(parent.x + 85 - 40 - 7 + 9, parent.y, parent.x + 85, parent.y + 11, 0xff3573d6);
        RenderUtil.drawImage(WATERCOLOR_SQUARES, (int) (parent.x + 85 - 40 - 4), parent.y, 13, 11);
        RenderUtil.drawImage(WATERCOLOR_ICONS, parent.x + 85 - 19, parent.y + 2, 17, 8);
        RenderUtil.drawImage(FOLDER_ICON, parent.x + 2, parent.y + 3, 9, 8);
        RenderUtil.drawRect(parent.x, parent.y, parent.x + 1, parent.y + 11, 0xff7db1fb);
        RenderUtil.drawRect(parent.x, parent.y, parent.x + 85, parent.y + 1, 0xff7db1fb);
        RenderUtil.drawRect(parent.x + 84, parent.y, parent.x + 85, parent.y + 11, 0xff7db1fb);
        sigmaFont.drawString(category.toString(), parent.x + 11 + 1, parent.y + 1 + (parent.arrayCount * 11), 0xff2e2e2e);
        GL11.glPopMatrix();
    }

    @Override
    public void drawButton(String text, Window parent, Button button, boolean toggled) {
        TTFFontRenderer sigmaFont = LunarPlus.getInstance().getFontManager().getFont("SEG 17");
        boolean haiku = false;
        GL11.glPushMatrix();
        RenderUtil.drawRect(parent.x, parent.y + (button.arrayCount * 11), parent.x + 85, parent.y + 11 + (button.arrayCount * 11), 0xffebebe4);
        RenderUtil.drawRect(parent.x, parent.y + (button.arrayCount) * 11, parent.x + 1, parent.y + (button.arrayCount + 1) * 11, 0xff7db1fb);
        RenderUtil.drawRect(parent.x + 84, parent.y + (button.arrayCount) * 11, parent.x + 85, parent.y + (button.arrayCount + 1) * 11, 0xff7db1fb);
        if (parent.buttons.indexOf(button) == parent.buttons.size() - 1) {
            RenderUtil.drawRect(parent.x, parent.y + (button.arrayCount) * 11 + 11, parent.x + 85 - 40 - 5 + 9, parent.y + (button.arrayCount) * 11 + 12, 0xff7db1fb);
            RenderUtil.drawRect(parent.x + 85 - 40 - 5 + 9, parent.y + (button.arrayCount) * 11 + 11, parent.x + 85, parent.y + (button.arrayCount) * 11 + 12, 0xff7db1fb);
        }
        sigmaFont.drawString(text, parent.x + 2, parent.y + 1 + (button.arrayCount * 11), toggled ? 0xff3573d6 : 0xff2e2e2e);
        GL11.glPopMatrix();
    }

}
