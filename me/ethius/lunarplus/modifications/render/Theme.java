package me.ethius.lunarplus.modifications.render;

import me.ethius.lunarplus.modifications.Mod;
import me.ethius.lunarplus.setting.Setting;

import java.util.Arrays;
import java.util.List;

public class Theme extends Mod {

    List<String> modes = Arrays.asList("watercolor");

    public final Setting theme = new Setting("theme", "watercolor", modes);
    public final Setting haiku = new Setting("haiku", false);

    public List<Setting> settings = Arrays.asList(theme, haiku);

    public Theme() {
        super("theme", KEY_UNBOUND, Category.render);
        super.settings = settings;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        setToggled(false);
    }

}
