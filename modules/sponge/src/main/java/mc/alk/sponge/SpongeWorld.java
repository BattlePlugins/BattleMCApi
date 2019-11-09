package mc.alk.sponge;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.MCWorld;
import mc.alk.mc.block.MCBlock;
import mc.alk.mc.block.MCChest;
import mc.alk.mc.block.MCSign;
import mc.alk.mc.util.MCWrapper;
import mc.alk.sponge.block.SpongeBlock;
import mc.alk.sponge.block.SpongeChest;
import mc.alk.sponge.block.SpongeSign;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.carrier.Chest;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class SpongeWorld extends MCWrapper<World> implements MCWorld {

    public SpongeWorld(World world) {
        super(world);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public SpongeBlock getBlockAt(int x, int y, int z) {
        BlockState block = handle.getBlock(x, y, z);
        if (handle.getTileEntity(x, y, z).isPresent()) {
            TileEntity tileEntity = handle.getTileEntity(x, y, z).get();
            if (block.getType().getName().toLowerCase().contains("sign")) {
                return new SpongeSign((Sign) tileEntity);
            }
            if (block.getType().getName().toLowerCase().contains("chest")) {
                return new SpongeChest((Chest) tileEntity);
            }
        }

        SpongeLocation location = (SpongeLocation) MCPlatform.getPlatform().getLocation(getName(), x, y, z);
        return new SpongeBlock(handle.getBlock(x, y, z).snapshotFor(location.getHandle()));
    }

    @Override
    public SpongeBlock getBlockAt(MCLocation loc) {
        return getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    @Override
    public <T extends MCBlock> T toType(MCBlock block, Class<T> clazz) throws ClassCastException {
        if (clazz.isAssignableFrom(block.getClass()))
            return clazz.cast(block);

        Location<World> loc = ((SpongeLocation) block.getLocation()).getHandle();
        BlockState blockState = loc.getBlock();
        if (!loc.getTileEntity().isPresent())
            throw new ClassCastException("Block can not be cast to " + clazz.getSimpleName());

        if (clazz == MCSign.class) {
            if (blockState.getType().getName().toLowerCase().contains("sign")) {
                return clazz.cast(new SpongeSign((Sign) loc.getTileEntity().get()));
            }
        }

        if (clazz == MCChest.class) {
            if (blockState.getType().getName().toLowerCase().contains("chest")) {
                return clazz.cast(new SpongeChest((Chest) loc.getTileEntity().get()));
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
        return "[World " + handle.getName() + "]";
    }
}
