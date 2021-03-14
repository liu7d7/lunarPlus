package me.ethius.lunarplus.event.impl.player;

import me.ethius.lunarplus.event.Event;

public final class MoveEvent extends Event {
   public double x;
   public double y;
   public double z;

   public MoveEvent(double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }
}
