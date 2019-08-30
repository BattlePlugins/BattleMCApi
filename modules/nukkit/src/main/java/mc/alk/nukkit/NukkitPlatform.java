package mc.alk.nukkit;

import cn.nukkit.IPlayer;
import cn.nukkit.OfflinePlayer;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;

import mc.alk.mc.APIType;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.chat.Message;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.MCWorld;
import mc.alk.nukkit.chat.NukkitMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class NukkitPlatform extends MCPlatform {

	@Override
	public APIType getAPIType() {
		return APIType.NUKKIT;
	}

	@Override
	public MCLocation getMCLocation(String world, double x, double y, double z) {
		return new NukkitLocation(world, x, y, z);
	}

	@Override
	public MCLocation getMCLocation(String world, double x, double y, double z, float pitch, float yaw) {
		return new NukkitLocation(world, x, y, z, pitch, yaw);
	}

	@Override
	public MCWorld getMCWorld(String world) {
		return new NukkitWorld(Server.getInstance().getLevelByName(world));
	}

	@Override
	public long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis) {
		return Server.getInstance().getScheduler().scheduleDelayedTask((Plugin) plugin, runnable, (int) millis/50).getTaskId();
	}

	@Override
	public MCPlayer getMCPlayer(String name) {
		return new NukkitPlayer(Server.getInstance().getPlayer(name));
	}

	@Override
	public MCOfflinePlayer getMCOfflinePlayer(String name) {
		IPlayer player = Server.getInstance().getOfflinePlayer(name);
		if (player instanceof OfflinePlayer)
			return new NukkitOfflinePlayer((OfflinePlayer) player);
		else if (player instanceof Player)
			return new NukkitPlayer((Player) player);

		return null;
	}

	@Override
	public MCOfflinePlayer getMCOfflinePlayer(UUID uuid) {
		IPlayer player = Server.getInstance().getOfflinePlayer(uuid);
		if (player instanceof OfflinePlayer)
			return new NukkitOfflinePlayer((OfflinePlayer) player);
		else if (player instanceof Player)
			return new NukkitPlayer((Player) player);

		return null;
	}

	@Override
	public Collection<MCPlayer> getMCOnlinePlayers() {
		Collection<MCPlayer> players = new ArrayList<>();
		for (Player player : Server.getInstance().getOnlinePlayers().values()) {
			players.add(new NukkitPlayer(player));
		}
		return players;
	}

	@Override
	public Collection<MCOfflinePlayer> getMCOfflinePlayers() {
		Collection<MCOfflinePlayer> players = new ArrayList<>();
		// TODO: Find a way to do this
		return players;
	}

	@Override
	public boolean isMCMainThread() {
		return Server.getInstance().isPrimaryThread();
	}

	@Override
	public boolean isMCOnlineMode() {
		return Server.getInstance().getProperties().getBoolean("xbox-auth");
	}

	@Override
	public String getMCVersion() {
		return "Bedrock-" + Server.getInstance().getVersion();
	}

	@Override
	public boolean cancelMCTask(long id) {
		Server.getInstance().getScheduler().cancelTask((int) id);
		return true;
	}

	@Override
	public Message getMCMessage() {
		return new NukkitMessage();
	}
}
