package me.ethius.lunarplus.modifications.render;

import me.ethius.lunarplus.event.EventTarget;
import me.ethius.lunarplus.event.Priority;
import me.ethius.lunarplus.event.impl.player.TickEvent;
import me.ethius.lunarplus.modifications.Mod;
import me.ethius.lunarplus.setting.Setting;

import java.util.Arrays;
import java.util.List;

public class Animation extends Mod {

    static final List<String> blockSettings = Arrays.asList("sigma", "slide", "avatar", "vanilla", "tap", "tap2");
    static final List<String> hitSettings = Arrays.asList("vanilla", "smooth");

    public static final Setting block = new Setting("block", "tap", blockSettings);
    public static final Setting hit = new Setting("hit", "vanilla", hitSettings);

    final List<Setting> settings = Arrays.asList(block, hit);

    public Animation() {
        super("animation", KEY_UNBOUND, Category.render);
        super.settings = settings;
    }

    @EventTarget(Priority.FIRST)
    public void onTick(TickEvent event) {
        if (mc.gameSettings.keyBindAttack.isKeyDown() && mc.thePlayer.isUsingItem()) {
            mc.thePlayer.swingItem();
        }
    }

}
