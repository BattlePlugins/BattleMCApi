package mc.alk.mc;

import mc.alk.mc.chat.Message;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.plugin.MCPlugin;

import java.util.Collection;
import java.util.UUID;

public abstract class MCPlatform {

	private static MCPlatform INSTANCE;
	private static APIType type;

	public static void setInstance(MCPlatform api){
		if (INSTANCE == null){
			INSTANCE = api;
			type = api.getAPIType();
		}
	}

	public static MCLocation getLocation(String world, double x, double y, double z, float pitch, float yaw){
		return INSTANCE.getMCLocation(world, x, y, z, pitch, yaw);
	}

	public static MCLocation getLocation(String world, double x, double y, double z){
		return INSTANCE.getMCLocation(world, x, y, z);
	}

	public static MCWorld getWorld(String world){
		return INSTANCE.getMCWorld(world);
	}

	public static long scheduleSyncDelayedTask(MCPlugin plugin, Runnable runnable){
		return scheduleSyncDelayedTask(plugin, runnable,0L);
	}

	public static long scheduleSyncDelayedTask(MCPlugin plugin, Runnable runnable, long millis) {
		return INSTANCE.scheduleSyncTask(plugin, runnable, millis);
	}

	public abstract MCLocation getMCLocation(String world, double x, double y, double z);
	public abstract MCLocation getMCLocation(String world, double x, double y, double z, float pitch, float yaw);

	public abstract MCWorld getMCWorld(String world);

	public abstract APIType getAPIType();

	public abstract long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis);
	public abstract boolean cancelMCTask(long id);

	public static int scheduleAsynchrounousTask(MCPlugin plugin, Runnable task) {
		return scheduleAsynchrounousTask(plugin, task, 0);
	}

	public static int scheduleAsynchrounousTask(MCPlugin plugin, Runnable task, long millis) {
		return Scheduler.scheduleAsynchrounousTask(task, millis);
	}

	public static MCPlayer getPlayer(String name) {
		return INSTANCE.getMCPlayer(name);
	}

	public abstract MCPlayer getMCPlayer(String name);

	public static APIType getType(){
		return type;
	}

	public static boolean cancelTask(long id) {
		return INSTANCE.cancelMCTask(id);
	}

	public abstract MCOfflinePlayer getMCOfflinePlayer(String name);

	public static MCOfflinePlayer getOfflinePlayer(String name) {
		return INSTANCE.getMCOfflinePlayer(name);
	}

	public abstract MCOfflinePlayer getMCOfflinePlayer(UUID uuid);

	public static MCOfflinePlayer getOfflinePlayer(UUID uuid) {
		return INSTANCE.getMCOfflinePlayer(uuid);
	}

	public abstract Collection<MCPlayer> getMCOnlinePlayers();

	public static Collection<MCPlayer> getOnlinePlayers() {
		return INSTANCE.getMCOnlinePlayers();
	}

	public abstract Collection<MCOfflinePlayer> getMCOfflinePlayers();

	public static Collection<MCOfflinePlayer> getOfflinePlayers() {
		return INSTANCE.getMCOfflinePlayers();
	}

	public abstract boolean isMCMainThread();

	public static boolean isMainThread() {
		return INSTANCE.isMCMainThread();
	}

	public abstract boolean isMCOnlineMode();

	public static boolean isOnlineMode() {
		return INSTANCE.isMCOnlineMode();
	}

	public abstract String getMCVersion();

	public static String getVersion() {
		return INSTANCE.getMCVersion();
	}

	public abstract Message getMCMessage();

	public static Message getPlatformMessage() {
		return INSTANCE.getMCMessage();
	}

	public static void broadcastMessage(Message message) {
		getOnlinePlayers().forEach(player -> player.sendMessage(message));
	}

	public abstract MCInventory createMCInventory(MCPlugin plugin, int slots, String title);

	public static MCInventory createInventory(MCPlugin plugin, int slots, String title) {
		return INSTANCE.createMCInventory(plugin, slots, title);
	}
}
