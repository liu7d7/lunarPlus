package me.ethius.lunarplus.modifications;

import me.ethius.lunarplus.LunarPlus;
import me.ethius.lunarplus.event.EventTarget;
import me.ethius.lunarplus.event.impl.system.KeyEvent;
import me.ethius.lunarplus.modifications.render.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModManager {

    public static ArrayList<Mod> mods = new ArrayList<>();

    public void init() {
        LunarPlus.getInstance().getEventManager().register(this);
        add(new HUD());
        add(new Theme());
        add(new ClickGUI());
        add(new ViewModel());
        add(new Animation());
    }

    public ArrayList<Mod> getMods() {
        return mods;
    }

    public void add(Mod mod) {
        mods.add(mod);
    }

    public static Mod getMod(Class clazz) {
        for (Mod mod : mods) {
            if (mod.getClass() == clazz) {
                return mod;
            }
        }
        return null;
    }

    public static final List<Mod> getModuleList(Mod.Category category) {
        List<Mod> list = new ArrayList<>();
        for (Mod module : mods) {
            if (module.getCategory().equals(category)) {
                list.add(module);
            }
        }
        // Organize alphabetically or ppl will get mad :D
        list.sort(Comparator.comparing(Mod::getName));
        return list;
    }

    public static Mod getModuleByName(String name) {
        for (Mod m : mods) {
            if (name.equalsIgnoreCase(m.getName()))
                return m;
        }
        return null;
    }

    @EventTarget
    public void onKey(KeyEvent event) {
        mods.stream().filter(m -> m.keybind == event.key).forEach(Mod::toggle);
    }

}
