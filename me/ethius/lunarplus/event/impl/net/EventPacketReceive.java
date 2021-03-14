package me.ethius.lunarplus.event.impl.net;

import me.ethius.lunarplus.event.Event;
import net.minecraft.network.Packet;

public class EventPacketReceive extends Event {
   public Packet packet;

   public EventPacketReceive(Packet packet) {
      this.packet = packet;
   }

   public Packet getPacket() {
      return this.packet;
   }

   public void setPacket(Packet packet) {
      this.packet = packet;
   }
}
