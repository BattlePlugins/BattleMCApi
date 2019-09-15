package mc.alk.bukkit;

import mc.alk.mc.MCLocation;
import mc.alk.mc.util.MCWrapper;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BukkitLocation extends MCWrapper<Location> implements MCLocation {

	public BukkitLocation(Location loc){
		super(loc);
	}

	public BukkitLocation(String world, double x, double y, double z) {
		super(new Location(Bukkit.getWorld(world), x, y, z));
	}

	public BukkitLocation(String world, double x, double y, double z, float pitch, float yaw) {
		super(new Location(Bukkit.getWorld(world), x, y, z, pitch, yaw));
	}

	@Override
	public BukkitWorld getWorld() {
		return new BukkitWorld(handle.getWorld());
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
		return handle.getPitch();
	}

	@Override
	public float getYaw() {
		return handle.getYaw();
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

	@Override
	public String toString(){
		return handle == null ? "[Location null]" :  "[Location "+ handle.getWorld()+" "+
				handle.getBlockX() +":"+ handle.getBlockY()+":" + handle.getBlockZ()+"]";
	}
}
