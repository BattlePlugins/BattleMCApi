package mc.alk.nukkit;

import cn.nukkit.Server;
import cn.nukkit.level.Location;

import mc.alk.mc.MCLocation;
import mc.alk.mc.util.MCWrapper;

public class NukkitLocation extends MCWrapper<Location> implements MCLocation {

    public NukkitLocation(Location loc){
        super(loc);
    }

    public NukkitLocation(String world, double x, double y, double z) {
        super(new Location(x, y, z, Server.getInstance().getLevelByName(world)));
    }

    public NukkitLocation(String world, double x, double y, double z, float pitch, float yaw) {
        super(new Location(x, y, z, pitch, yaw, Server.getInstance().getLevelByName(world)));
    }

    @Override
    public NukkitWorld getWorld() {
        return new NukkitWorld(handle.getLevel());
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
        return (float) handle.getPitch();
    }

    @Override
    public float getYaw() {
        return (float) handle.getYaw();
    }

    @Override
    public int getBlockX() {
        return handle.getFloorX();
    }

    @Override
    public int getBlockY() {
        return handle.getFloorY();
    }

    @Override
    public int getBlockZ() {
        return handle.getFloorZ();
    }

    @Override
    public String toString(){
        return handle == null ? "[Location null]" :  "[Location "+handle.getLevel()+" "+
                handle.getX() +":"+ handle.getY()+":"+handle.getZ()+"]";
    }
}
