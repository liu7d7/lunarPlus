package me.ethius.lunarplus.event.impl.system;

import me.ethius.lunarplus.event.Event;

public class KeyEvent extends Event {

    public int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
