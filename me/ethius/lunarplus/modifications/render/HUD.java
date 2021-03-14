package me.ethius.lunarplus.modifications.render;

import me.ethius.lunarplus.ColorUtil;
import me.ethius.lunarplus.LunarPlus;
import me.ethius.lunarplus.event.EventTarget;
import me.ethius.lunarplus.event.impl.system.Event2D;
import me.ethius.lunarplus.font.TTFFontRenderer;
import me.ethius.lunarplus.modifications.Mod;
import me.ethius.lunarplus.modifications.ModManager;
import me.ethius.lunarplus.setting.Setting;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class HUD extends Mod {

    public final Setting watermark = new Setting("watermark", true);
    public final Setting coords = new Setting("co-ords", true);
    public final Setting speed = new Setting("bps", true);
    public final Setting brand = new Setting("server-brand", true);
    public final Setting status = new Setting("status-effects", true);
    public final Setting inv = new Setting("inv-viewer", true);
    public final Setting invX = new Setting("inv-x", 572, 0.0, 1920, true);
    public final Setting invY = new Setting("inv-y", 464, 0.0, 1920, true);
    public final Setting renderText = new Setting("render-text", false);

    List<Setting> settings = Arrays.asList(watermark, coords, speed, status, brand, inv, invX, invY);

    int row;
    FontRenderer fontRenderer = mc.fontRendererObj;
    TTFFontRenderer sigmaFont;

    public HUD() {
        super("hud", KEY_UNBOUND, Category.render);
        super.settings = settings;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        sigmaFont = LunarPlus.getInstance().getFontManager().getFont("SFR 19");
    }

    @EventTarget
    public void onUpdate(Event2D event) {
        if (nullCheck()) return;
        row = 0;
        if (watermark.getValBoolean()) {
            drawPulsiveWatermark(true, (int) (((float) mc.displayWidth / mc.gameSettings.guiScale) - sigmaFont.getWidth(LunarPlus.NAME + LunarPlus.VERSION) + 1), (int) ((float) mc.displayHeight / mc.gameSettings.guiScale) - ((row * 11) + 12));
        }
        if (coords.getValBoolean()) {
            sigmaFont.drawStringWithShadow(getFullCoords(), ((float) mc.displayWidth / mc.gameSettings.guiScale) / 2 - ((float) sigmaFont.getWidth(getFullCoords()) / 2), 10, 0xffffffff);
        }
        if (speed.getValBoolean()) {
            sigmaFont.drawStringWithShadow(getBPS(), ((float) mc.displayWidth / mc.gameSettings.guiScale) - ((float) sigmaFont.getWidth(getBPS())), ((float) mc.displayHeight / mc.gameSettings.guiScale) - ((++row * 11) + 12), 0xffffffff);
        }
        if (brand.getValBoolean()) {
            sigmaFont.drawStringWithShadow(mc.thePlayer.getClientBrand(), ((float) mc.displayWidth / mc.gameSettings.guiScale) - ((float) sigmaFont.getWidth(mc.thePlayer.getClientBrand())), ((float) mc.displayHeight / mc.gameSettings.guiScale) - ((++row * 11) + 12), 0xffffffff);
        }
        if (inv.getValBoolean()) {
            for (int i = 0; i < 27; i++) {
                ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i + 9);
                int offsetX = (int) invX.getValDouble() + (i % 9) * 16;
                int offsetY = (int) invY.getValDouble() + (i / 9) * 16;
                if (itemStack != null) {
                    RenderHelper.enableStandardItemLighting();
                    mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, offsetX, offsetY);
                    RenderHelper.disableStandardItemLighting();
                    if (renderText.getValBoolean()) mc.getRenderItem().renderItemOverlays(fontRenderer, itemStack, offsetX, offsetY);
                }
            }
        }
        if (status.getValBoolean()) {
            for (PotionEffect effect : mc.thePlayer.getActivePotionEffects()) {
                String line = formatEff(effect);
                sigmaFont.drawStringWithShadow(line.substring(7).toLowerCase(), ((float) mc.displayWidth / mc.gameSettings.guiScale) - ((float) sigmaFont.getWidth(line.substring(7).toLowerCase())), ((float) mc.displayHeight / mc.gameSettings.guiScale) - ((++row * 11) + 11) - 1, 0xffffffff);
            }
        }
    }

    private String formatEff(PotionEffect statusEffectInstance) {
        return String.format("%s %d [%s]", statusEffectInstance.getEffectName(), statusEffectInstance.getAmplifier() + 1, durationToString(statusEffectInstance, 1));
    }

    public void drawPulsiveWatermark(boolean topLeft, int x, int y) {
        sigmaFont.drawStringWithShadow(this.getNameParts(LunarPlus.NAME)[0], x + 2, y + 1, ColorUtil.getRainbow(0.3f,  1.0f, 10, 0));
        sigmaFont.drawStringWithShadow(this.getNameParts(LunarPlus.NAME)[1], x + sigmaFont.getWidth(this.getNameParts(LunarPlus.NAME)[0]), y + 1, -1);
        sigmaFont.drawStringWithShadow(LunarPlus.VERSION, x + sigmaFont.getWidth(LunarPlus.NAME), y + 1, ColorUtil.getRainbow(0.3f,  1.0f, 10, 0));
    }

    private String getFullCoords() { // r333mo 01/01/2020 ported from the logic i wrote in machack
        DecimalFormat df = new DecimalFormat("#.##");
        df.setGroupingUsed(true);   // so we get commas in coords so we can actually read them
        df.setGroupingSize(3);

        // coord variables we need
        BlockPos pos = mc.thePlayer.getPosition();
        BlockPos pos2 = new BlockPos(pos.getX() / 8, pos.getY(), pos.getZ() / 8);
        BlockPos pos3 = new BlockPos(pos.getX() * 8, pos.getY(), pos.getZ() * 8);

        // initialise stuff
        String coords = "";
        String other_dim_coords = "";

        if (mc.thePlayer.dimension == 1) {
            other_dim_coords = "";  // no need for this

            coords = (df.format(pos.getX()) + " "
                    + df.format(pos.getY()) + " "
                    + df.format(pos.getZ()) + " "
                    + other_dim_coords);

        } else if (mc.thePlayer.dimension == -1) {
            other_dim_coords = df.format(pos3.getX()) + " "
                    + df.format(pos3.getY()) + " "
                    + df.format(pos3.getZ());

            coords = (df.format(pos.getX()) + " "
                    + df.format(pos.getY()) + " "
                    + df.format(pos.getZ()) + " "
                    + "\u00a77[\u00a7f" + other_dim_coords + "\u00a77]");

        } else if (mc.thePlayer.dimension == 0) {
            other_dim_coords = (df.format(pos2.getX()) + " "
                    + df.format(pos2.getY()) + " "
                    + df.format(pos2.getZ()));

            coords = (df.format(pos.getX()) + " "
                    + df.format(pos.getY()) + " "
                    + df.format(pos.getZ()) + " "
                    + "\u00a77[\u00a7f" + other_dim_coords + "\u00a77]");
        }

        return "xyz " + coords;
    }

    private String getBPS() {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        final double deltaX = Math.abs(mc.thePlayer.posX - mc.thePlayer.lastTickPosX);
        final double deltaZ = Math.abs(mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ);
        String bps = decimalFormat.format((deltaX + deltaZ) * 20);
        return "bps: " + bps;
    }

    public int getBottom() {
        return (row + 1) * 14;
    }

    public static String durationToString(PotionEffect effect, float multiplier) {
        if (effect.getIsPotionDurationMax()) {
            return "**:**";
        } else {
            int i = MathHelper.floor_double((float)effect.getDuration() * multiplier);
            return ticksToString(i);
        }
    }

    public static String ticksToString(int ticks) {
        int i = ticks / 20;
        int j = i / 60;
        i %= 60;
        return i < 10 ? j + ":0" + i : j + ":" + i;
    }

    public String[] getNameParts(String string) {
        String firstLetter = string.substring(0,1); // takes first letter
        String restOfString = string.substring(1); // takes rest of name
        return new String[]{firstLetter, restOfString};
    }

}
