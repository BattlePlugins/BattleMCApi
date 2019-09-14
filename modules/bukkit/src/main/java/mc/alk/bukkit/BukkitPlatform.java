package mc.alk.bukkit;

import mc.alk.bukkit.chat.BukkitMessage;
import mc.alk.bukkit.chat.SpigotMessage;
import mc.alk.bukkit.inventory.BukkitInventory;
import mc.alk.mc.APIType;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.chat.Message;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.MCWorld;
import mc.alk.mc.plugin.PlatformPlugin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class BukkitPlatform extends MCPlatform {

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
	public long scheduleSyncTask(PlatformPlugin plugin, Runnable runnable, long millis) {
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
	public boolean isMCOnlineMode() {
		return Bukkit.getOnlineMode();
	}

	@Override
	public String getMCVersion() {
		return "Java-" + Bukkit.getVersion();
	}

	@Override
	public Message getMCMessage() {
		if (isSpigot())
			return new SpigotMessage();

		return new BukkitMessage();
	}

	@Override
	public MCInventory createMCInventory(MCPlugin plugin, int slots, String title) {
		return new BukkitInventory(Bukkit.createInventory(null, slots, title));
	}

	@Override
	public boolean cancelMCTask(long id) {
		Bukkit.getScheduler().cancelTask((int)id);
		return true;
	}

	public boolean isSpigot() {
		try {
			Class.forName("org.spigotmc.SpigotConfig");
			return true;
		} catch (Throwable ex) {
			/* do nothing */
		}

		return false;
	}

	public boolean isPaper() {
		try {
			Class.forName("com.destroystokyo.paper.PaperConfig");
			return true;
		} catch (Throwable ex) {
			/* do nothing */
		}

		return false;
	}
}
