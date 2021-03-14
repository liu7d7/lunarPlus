package me.ethius.lunarplus;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.ethius.lunarplus.setting.Timer;

import java.awt.*;

public class ColorUtil {

    static Timer timer = new Timer();

    public static Color guiColour() {
        return (new Color(128, 134, 255));
    }

    public static int guiColourWatercolor() {
        return 0xff3573d6;
    }

    public static int guiColourWatercolorLessSat() {
        return 0xcc3573d6;
    }

    public static int getRainbow(float sat, float bri, double speed, int offset) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + offset) / speed);
        rainbowState %= 360.0;
        return Color.HSBtoRGB((float) (rainbowState / 360.0), sat, bri);
    }

    public static int getRainbow(double speed, int offset) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + offset) / speed);
        rainbowState %= 360.0;
        return (int) (rainbowState / 360.0);
    }

    public static ChatFormatting getRainbowFormatting() {
        if (timer.passed(3000)) {
            timer.reset();
        }
        if (timer.passed(2700)) {
            return ChatFormatting.WHITE;
        }
        if (timer.passed(2400)) {
            return ChatFormatting.GRAY;
        }
        if (timer.passed(2100)) {
            return ChatFormatting.BLUE;
        }
        if (timer.passed(1800)) {
            return ChatFormatting.DARK_AQUA;
        }
        if (timer.passed(1500)) {
            return ChatFormatting.DARK_GREEN;
        }
        if (timer.passed(1200)) {
            return ChatFormatting.GREEN;
        }
        if (timer.passed(900)) {
            return ChatFormatting.YELLOW;
        }
        if (timer.passed(600)) {
            return ChatFormatting.GOLD;
        }
        if (timer.passed(300)) {
            return ChatFormatting.RED;
        }
        if (timer.passed(1)) {
            return ChatFormatting.DARK_RED;
        }
        return ChatFormatting.GRAY;
    }

    public static String getPingColor(int ping) {
        if (ping < 100) {
            return "\u00a7a";
        } else if (ping < 150) {
            return "\u00a7c";
        } else {
            return "\u00a74";
        }
    }

}