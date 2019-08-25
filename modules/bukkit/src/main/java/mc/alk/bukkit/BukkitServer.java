package mc.alk.bukkit;

import mc.alk.mc.APIType;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.mc.MCServer;
import mc.alk.mc.MCWorld;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class BukkitServer extends MCServer {

	@Override
	public APIType getAPIType() {
		return APIType.BUKKIT;
	}

	@Override
	public MCLocation getMCLocation(String world, double x, double y, double z) {
		return new BukkitLocation(world, x, y, z);
	}

	@Override
	public MCLocation getMCLocation(String world, double x, double y, double z, float pitch, float yaw) {
		return new BukkitLocation(world, x, y, z, pitch, yaw);
	}

	@Override
	public MCWorld getMCWorld(String world) {
		return new BukkitWorld(Bukkit.getWorld(world));
	}

	@Override
	public long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis) {
		return Bukkit.getScheduler().scheduleSyncDelayedTask((JavaPlugin) plugin, runnable,millis/50);
	}

	@Override
	public MCPlayer getMCPlayer(String name) {
		Player p = Bukkit.getPlayer(name);
		return p == null ? null : new BukkitPlayer(p);
	}

	@Override
	public MCOfflinePlayer getMCOfflinePlayer(String name) {
		return new BukkitOfflinePlayer(Bukkit.getOfflinePlayer(name));
	}

	@Override
	public MCOfflinePlayer getMCOfflinePlayer(UUID uuid) {
		return new BukkitOfflinePlayer(Bukkit.getOfflinePlayer(uuid));
	}

	@Override
	public Collection<MCPlayer> getMCOnlinePlayers() {
		Collection<MCPlayer> players = new ArrayList<>();
		for (Player player : Bukkit.getOnlinePlayers()) {
			players.add(new BukkitPlayer(player));
		}
		return players;
	}

	@Override
	public Collection<MCOfflinePlayer> getMCOfflinePlayers() {
		Collection<MCOfflinePlayer> players = new ArrayList<>();
		for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			players.add(new BukkitOfflinePlayer(player));
		}
		return players;
	}

	@Override
	public boolean isMCMainThread() {
		return Bukkit.isPrimaryThread();
	}

    @Override
	public boolean cancelMCTask(long id) {
		Bukkit.getScheduler().cancelTask((int)id);
		return true;
	}
}
