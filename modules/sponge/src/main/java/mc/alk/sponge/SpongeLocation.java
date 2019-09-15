package mc.alk.sponge;

import mc.alk.mc.MCLocation;
import mc.alk.mc.util.MCWrapper;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class SpongeLocation extends MCWrapper<Location<World>> implements MCLocation {

    public SpongeLocation(Location loc){
        super(loc);
    }

    public SpongeLocation(String world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public SpongeLocation(String world, double x, double y, double z, float pitch, float yaw) {
        super(new Location<>(Sponge.getServer().getWorld(world).get(), x, y, z));
    }
    @Override
    public SpongeWorld getWorld() {
        return new SpongeWorld(handle.getExtent());
    }

    @Override
    public double getX() {
        return handle.getX();
    }

    @Override
    public double getY() {
        return handle.getY();
    }

    @Override
    public double getZ() {
        return handle.getZ();
    }

    @Override
    public float getPitch() {
        return 0;
    }

    @Override
    public float getYaw() {
        return 0;
    }

    @Override
    public int getBlockX() {
        return handle.getBlockX();
    }

    @Override
    public int getBlockY() {
        return handle.getBlockY();
    }

    @Override
    public int getBlockZ() {
        return handle.getBlockZ();
    }
}
