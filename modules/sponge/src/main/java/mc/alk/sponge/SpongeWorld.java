package mc.alk.sponge;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;
import mc.alk.mc.block.MCBlock;
import mc.alk.mc.block.MCChest;
import mc.alk.mc.block.MCSign;
import mc.alk.sponge.block.SpongeChest;
import mc.alk.sponge.block.SpongeSign;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class SpongeWorld implements MCWorld {

    private World world;

    public SpongeWorld(World world) {
        this.world = world;
    }

    @Override
    public String getName() {
        return world.getName();
    }

    @Override
    public MCBlock getBlockAt(int x, int y, int z) {
        BlockState block = world.getBlock(x, y, z);
        if (block.getType().getName().toLowerCase().contains("sign")) {
            // return sponge sign
        }
        if (block.getType().getName().toLowerCase().contains("chest")) {
            // return sponge chest
        }

        // return normal block
        return null;
    }

    @Override
    public MCBlock getBlockAt(MCLocation loc) {
        return getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    @Override
    public MCBlock toType(MCBlock block, Class<? extends MCBlock> clazz) throws ClassCastException {
        if (clazz.isAssignableFrom(block.getClass()))
            return block;

        Location loc = ((SpongeLocation) block.getLocation()).getSpongeLocation();
        BlockState blockState = loc.getBlock();
        if (blockState == null)
            return null;

        if (clazz == MCSign.class) {
            if (blockState.getType().getName().toLowerCase().contains("sign")) {
                return new SpongeSign((Sign) loc.getTileEntity().get());
            }
        }

        if (clazz == MCChest.class) {
            if (blockState.getType().getName().toLowerCase().contains("chest")) {
                return new SpongeChest((Chest) loc.getTileEntity().get());
            }
        }

        throw new ClassCastException("Block can not be cast to " + clazz.getSimpleName());
    }

    @Override
    public boolean isType(MCBlock block, Class<? extends MCBlock> clazz) {
        try {
            MCBlock b = toType(block,clazz);
            return b != null;
        } catch (ClassCastException e){
            return false;
        }
    }

    @Override
    public String toString(){
        return "[World " + world.getName() + "]";
    }
}
