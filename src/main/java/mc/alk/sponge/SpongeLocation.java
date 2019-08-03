package mc.alk.sponge;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class SpongeLocation implements MCLocation {

    private Location<World> loc;

    public SpongeLocation(Location<World> loc) {
        this.loc = loc;
    }

    @Override
    public MCWorld getWorld() {
        return new SpongeWorld(loc.getExtent());
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
