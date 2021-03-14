package me.ethius.lunarplus;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class MathUtil {

    static Minecraft mc = Minecraft.getMinecraft();

    public static double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        } else {
            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
    }

    public static double getIncremental(double val, double inc) {
        double one = 1.0D / inc;
        return (double)Math.round(val * one) / one;
    }

    public static double randomNumber(double max, double min) {
        return Math.random() * (max - min) + min;
    }

    public static float getRandomInRange(float min, float max) {
        Random random = new Random();
        float range = max - min;
        float scaled = random.nextFloat() * range;
        float shifted = scaled + min;
        return shifted;
    }

    public static boolean isInteger(Double variable) {
        return variable == Math.floor(variable) && !Double.isInfinite(variable);
    }

    public float round(float value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    public static double round(final double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double round(double num, double increment) {
        if (increment < 0.0D) {
            throw new IllegalArgumentException();
        } else {
            BigDecimal bd = new BigDecimal(num);
            bd = bd.setScale((int)increment, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
    }

    public static int getYawNeeded(double x, double z) {
        double diffX = x - mc.thePlayer.posX;
        double diffZ = z - mc.thePlayer.posZ;
        float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90F;
        return (int) (mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - mc.thePlayer.rotationYaw));
    }
}
