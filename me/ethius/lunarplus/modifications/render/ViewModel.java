package me.ethius.lunarplus.modifications.render;

import me.ethius.lunarplus.event.EventTarget;
import me.ethius.lunarplus.event.impl.player.TickEvent;
import me.ethius.lunarplus.modifications.Mod;
import me.ethius.lunarplus.setting.Setting;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Mouse;

import java.util.Arrays;
import java.util.List;

public class ViewModel extends Mod {

    public Setting PosX = new Setting("x-pos", 0d, -3d, 3d, 0.1d, false);
    public Setting PosY = new Setting("y-pos", 0d, -3d, 3d, 0.1d, false);
    public Setting PosZ = new Setting("z-pos", 0d, -3d, 3d, 0.1d, false);

    public Setting ScaleX = new Setting("x-scale", 1d, -3d, 3d, 0.1d, false);
    public Setting ScaleY = new Setting("y-scale", 1d, -3d, 3d, 0.1d, false);
    public Setting ScaleZ = new Setting("z-scale", 1d, -3d, 3d, 0.1d, false);

    public Setting ProgressRight = new Setting("progress-right", 1f, -0.1f, 1f, 0.1f, false);

    public Setting LockPos = new Setting("vanilla", false);

    final List<Setting> settingList = Arrays.asList(PosX, PosY, PosZ, ScaleX, ScaleY, ScaleZ, ProgressRight, LockPos);

    public ViewModel() {
        super("view-model", KEY_UNBOUND, Category.render);
        super.settings = settingList;
    }

    @EventTarget
    public void onTick(TickEvent event) {
        if (nullCheck()) return;
        if (LockPos.getValBoolean()) {
            PosX.setValDouble(0d);
            PosY.setValDouble(0d);
            PosZ.setValDouble(0d);
            ScaleZ.setValDouble(1d);
            ScaleY.setValDouble(1d);
            ScaleX.setValDouble(1d);
            ProgressRight.setValDouble(1d);
        }
        if (ProgressRight.getValDouble() >= 0) {
            mc.getItemRenderer().itemToRender = mc.thePlayer.inventory.getStackInSlot(mc.thePlayer.inventory.currentItem);
            mc.getItemRenderer().equippedProgress = (float) (ProgressRight.getValDouble());
        }
    }

}
