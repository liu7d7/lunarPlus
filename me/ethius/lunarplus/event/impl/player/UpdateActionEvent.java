package me.ethius.lunarplus.event.impl.player;

import me.ethius.lunarplus.event.Event;

public final class UpdateActionEvent extends Event {
   private boolean sprintState;
   private boolean sneakState;

   public UpdateActionEvent(boolean sprintState, boolean sneakState) {
      this.sprintState = sprintState;
      this.sneakState = sneakState;
   }

   public boolean isSprintState() {
      return this.sprintState;
   }

   public void setSprintState(boolean sprintState) {
      this.sprintState = sprintState;
   }

   public boolean isSneakState() {
      return this.sneakState;
   }

   public void setSneakState(boolean sneakState) {
      this.sneakState = sneakState;
   }
}
