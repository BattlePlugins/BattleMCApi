package mc.alk.sponge.block;

import mc.alk.mc.block.MCBlock;
import mc.alk.mc.util.MCWrapper;
import mc.alk.sponge.SpongeLocation;
import mc.alk.sponge.SpongeWorld;

import org.spongepowered.api.block.BlockSnapshot;

public class SpongeBlock extends MCWrapper<BlockSnapshot> implements MCBlock {

    public SpongeBlock(BlockSnapshot block) {
        super(block);
    }

    @Override
    public SpongeWorld getWorld() {
        return new SpongeWorld(handle.getLocation().get().getExtent());
    }

    @Override
    public SpongeLocation getLocation() {
        return new SpongeLocation(handle.getLocation().get());
    }

    @Override
    public int getX() {
        return handle.getLocation().get().getBlockX();
    }

    @Override
    public int getY() {
        return handle.getLocation().get().getBlockY();
    }

    @Override
    public int getZ() {
        return handle.getLocation().get().getBlockZ();
    }

    @Override
    public String getType() {
        return handle.getState().getType().getName();
    }

    @Override
    public void update() {
        // TODO: Add API here
    }

    @Override
    public void update(boolean b) {
        // TODO: Add API here
    }

    @Override
    public SpongeBlock clone() {
        return new SpongeBlock(handle);
    }
}
