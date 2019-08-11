package mc.alk.nukkit;

import cn.nukkit.Server;
import cn.nukkit.level.Location;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

public class NukkitLocation implements MCLocation {

	private Location loc;

	public NukkitLocation(Location loc){
		this.loc = loc;
	}

	public NukkitLocation(String world, double x, double y, double z) {
		this.loc = new Location(x, y, z, Server.getInstance().getLevelByName(world));
	}

	public NukkitLocation(String world, double x, double y, double z, float pitch, float yaw) {
		this.loc = new Location(x, y, z, pitch, yaw, Server.getInstance().getLevelByName(world));
	}

	@Override
	public MCWorld getWorld() {
		return new NukkitWorld(loc.getLevel());
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
		return (float) loc.getPitch();
	}

	@Override
	public float getYaw() {
		return (float) loc.getYaw();
	}

	@Override
	public int getBlockX() {
		return loc.getFloorX();
	}

	@Override
	public int getBlockY() {
		return loc.getFloorY();
	}

	@Override
	public int getBlockZ() {
		return loc.getFloorZ();
	}

	public Location getNukkitLocation() {
		return loc;
	}

	@Override
	public String toString(){
		return loc == null ? "[Location null]" :  "[Location "+loc.getLevel()+" "+
				loc.getX() +":"+ loc.getY()+":"+loc.getZ()+"]";
	}
}
