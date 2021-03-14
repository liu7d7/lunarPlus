package me.ethius.lunarplus.setting;

import me.ethius.lunarplus.LunarPlus;
import me.ethius.lunarplus.modifications.Mod;
import net.minecraft.util.MathHelper;

import java.util.List;

public class Setting {
    private String name;
    private Mod parent;
    private String mode;

    private String sval;
    private List<String> options;

    private boolean bval;

    private double inc;
    private double dval;
    private double min;
    private double max;
    private boolean onlyint = false;


    public Setting(String name, String sval, List<String> options) {
        this.name = name;
        this.sval = sval;
        this.options = options;
        this.mode = "Combo";
    }

    public Setting(String name, boolean bval) {
        this.name = name;
        this.bval = bval;
        this.mode = "Check";
    }

    public Setting(String name, double dval, double min, double max, boolean onlyint) {
        this.name = name;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
    }

    public Setting(String name, double dval, double min, double max, double inc, boolean onlyint) {
        this.name = name;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.inc = inc;
        this.mode = "Slider";
    }

    public double getInc() {
        return inc;
    }

    public String getName() {
        return name;
    }

    public Mod getParentMod() {
        return parent;
    }

    public String getValString() {
        return this.sval;
    }

    public void setValString(String in) {
        this.sval = in;
        if (parent != null && LunarPlus.getInstance().getSaveConfig() != null) LunarPlus.getInstance().getSaveConfig().saveIndividualModule(parent);
    }

    public List<String> getOptions() {
        return this.options;
    }

    public boolean getValBoolean() {
        return this.bval;
    }

    public void setValBoolean(boolean in) {
        this.bval = in;
        if (parent != null && LunarPlus.getInstance().getSaveConfig() != null) LunarPlus.getInstance().getSaveConfig().saveIndividualModule(parent);
    }

    public double getValDouble() {
        if(this.onlyint) {
            this.dval = (int)dval;
        }
        return this.dval;
    }

    public void setValDouble(double in) {
        this.dval = MathHelper.clamp_double(in, min, max);
        if (parent != null && LunarPlus.getInstance().getSaveConfig() != null) LunarPlus.getInstance().getSaveConfig().saveIndividualModule(parent);
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public boolean isCombo() {
        return this.mode.equalsIgnoreCase("Combo") ? true : false;
    }

    public boolean isCheck() {
        return this.mode.equalsIgnoreCase("Check") ? true : false;
    }

    public boolean isSlider() {
        return this.mode.equalsIgnoreCase("Slider") ? true : false;
    }

    public boolean onlyInt() {
        return this.onlyint;
    }

    public String getNextInList(boolean p_Reverse)
    {
        final String l_CurrEnum = (String) this.getValString();

        int i = 0;

        for (; i < this.options.size(); i++)
        {
            final String e = (String) this.options.get(i);
            if (e.equalsIgnoreCase(l_CurrEnum))
            {
                break;
            }
        }

        return this.options.get((p_Reverse ? (i != 0 ? i - 1 : this.options.size() - 1)
                : i + 1) % this.options.size());
    }


}
