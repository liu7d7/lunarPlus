package me.ethius.lunarplus.event.impl.system;

import me.ethius.lunarplus.event.Event;

public class Event3D extends Event {

    public float partialTicks;

    public Event3D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

}
