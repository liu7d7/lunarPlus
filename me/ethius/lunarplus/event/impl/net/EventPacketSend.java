package me.ethius.lunarplus.event.impl.net;

import me.ethius.lunarplus.event.Event;
import net.minecraft.network.Packet;

public class EventPacketSend extends Event {
   public Packet packet;

   public EventPacketSend(Packet packet) {
      this.packet = packet;
   }
}
