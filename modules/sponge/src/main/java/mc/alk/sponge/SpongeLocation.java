package mc.alk.sponge;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class SpongeLocation implements MCLocation {

    private Location<World> loc;

    public SpongeLocation(Location loc){
        this.loc = loc;
    }

    public SpongeLocation(String world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public SpongeLocation(String world, double x, double y, double z, float pitch, float yaw) {
        this.loc = new Location<World>(Sponge.getServer().getWorld(world).get(), x, y, z);
    }
    @Override
    public MCWorld getWorld() {
        return new SpongeWorld(loc.getExtent());
    }

    @Override
    public double getX() {
        return loc.getX();
    }

    @Override
    public double getY() {
        return loc.getY();
    }

    @Override
    public double getZ() {
        return loc.getZ();
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
        return loc.getBlockX();
    }

    @Override
    public int getBlockY() {
        return loc.getBlockY();
    }

    @Override
    public int getBlockZ() {
        return loc.getBlockZ();
    }

    public Location<World> getSpongeLocation() {
        return loc;
    }
}
