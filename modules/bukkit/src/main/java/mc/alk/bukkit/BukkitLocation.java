package mc.alk.bukkit;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BukkitLocation implements MCLocation {

	private Location loc;

	public BukkitLocation(Location loc){
		this.loc = loc;
	}

	public BukkitLocation(String world, int x, int y, int z) {
		this.loc = new Location(Bukkit.getWorld(world),x,y,z);
	}

	@Override
	public MCWorld getWorld() {
		return new BukkitWorld(loc.getWorld());
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

	public Location getBukkitLocation() {
		return loc;
	}

	@Override
	public String toString(){
		return loc == null ? "[Location null]" :  "[Location "+loc.getWorld()+" "+
				loc.getBlockX() +":"+ loc.getBlockY()+":"+loc.getBlockZ()+"]";
	}
}
