package mc.alk.sponge.block;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;
import mc.alk.mc.block.MCBlock;
import mc.alk.sponge.SpongeLocation;
import mc.alk.sponge.SpongeWorld;

import org.spongepowered.api.block.BlockSnapshot;

public class SpongeBlock implements MCBlock {

    private BlockSnapshot block;

    public SpongeBlock(BlockSnapshot block) {
        this.block = block;
    }

    @Override
    public MCWorld getWorld() {
        return new SpongeWorld(block.getLocation().get().getExtent());
    }

    @Override
    public MCLocation getLocation() {
        return new SpongeLocation(block.getLocation().get());
    }

    @Override
    public int getX() {
        return block.getLocation().get().getBlockX();
    }

    @Override
    public int getY() {
        return block.getLocation().get().getBlockY();
    }

    @Override
    public int getZ() {
        return block.getLocation().get().getBlockZ();
    }

    @Override
    public String getType() {
        return block.getState().getType().getName();
    }

    @Override
    public void update(boolean b) {
        // TODO: Add API here
    }

    @Override
    public SpongeBlock clone() {
        return new SpongeBlock(block);
    }

    public BlockSnapshot getSpongeBlock() {
        return block;
    }
}
