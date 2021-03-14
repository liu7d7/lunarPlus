package me.ethius.lunarplus.event.impl.player;

import me.ethius.lunarplus.event.Event;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class BlockBreakEvent extends Event {

    public EnumFacing facing;
    public BlockPos pos;

    public BlockBreakEvent(BlockPos pos, EnumFacing facing) {
        this.pos = pos;
        this.facing = facing;
    }

}
