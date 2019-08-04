package mc.alk.nukkit;

import cn.nukkit.level.Location;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

public class NukkitLocation implements MCLocation {

	private Location loc;

	public NukkitLocation(Location loc){
		this.loc = loc;
	}

	public NukkitLocation(String world, int x, int y, int z) {
		this.loc = new Location(x, y, z);
	}

	@Override
	public MCWorld getWorld() {
		return new NukkitWorld(loc.getLevel());
	}

	@Override
	public int getBlockX() {
		return (int) loc.getX();
	}

	@Override
	public int getBlockY() {
		return (int) loc.getY();
	}

	@Override
	public int getBlockZ() {
		return (int) loc.getZ();
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
