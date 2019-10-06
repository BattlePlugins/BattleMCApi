package mc.alk.mc;

import mc.alk.mc.chat.Message;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.mc.plugin.MCPluginManager;
import mc.alk.mc.plugin.MCServicePriority;
import mc.alk.mc.scheduler.Scheduler;

import java.util.Collection;
import java.util.UUID;

public abstract class MCPlatform {

    private static MCPlatform INSTANCE;
    private static APIType type;

    private static MCPluginManager pluginManager = new MCPluginManager();

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

    public static long scheduleSyncRepeatingTask(MCPlugin plugin, Runnable runnable, long millis) {
        return INSTANCE.scheduleSyncRepeatingTask(plugin, runnable, millis);
    }

    public abstract MCLocation getMCLocation(String world, double x, double y, double z);
    public abstract MCLocation getMCLocation(String world, double x, double y, double z, float pitch, float yaw);

    public abstract MCWorld getMCWorld(String world);

    public abstract APIType getAPIType();

    public abstract long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis);
    public abstract long scheduleRepeatingTask(MCPlugin plugin, Runnable runnable, long millis);

    public abstract boolean cancelMCTask(long id);

    public static int scheduleAsynchrounousTask(Runnable task) {
        return scheduleAsynchrounousTask(task, 0);
    }

    public static int scheduleAsynchrounousTask(Runnable task, long millis) {
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

    public abstract Message getDefaultMCMessage();

    public static Message getDefaultPlatformMessage() {
        return INSTANCE.getDefaultMCMessage();
    }

    public abstract MCItemStack getDefaultMCItemStack();

    public static MCItemStack getDefaultPlatformItemStack() {
        return INSTANCE.getDefaultMCItemStack();
    }

    public static void broadcastMessage(Message message) {
        getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }

    public abstract MCInventory createMCInventory(MCPlugin plugin, int slots, String title);

    public static MCInventory createInventory(MCPlugin plugin, int slots, String title) {
        return INSTANCE.createMCInventory(plugin, slots, title);
    }

    public abstract <T> void registerMCService(Class<T> clazz, T service, MCPlugin plugin, MCServicePriority priority);

    public static <T> void registerService(Class<T> clazz, T service, MCPlugin plugin, MCServicePriority priority) {
        INSTANCE.registerMCService(clazz, service, plugin, priority);
    }

    public abstract <T> T getMCService(Class<T> clazz);

    public static <T> T getService(Class<T> clazz) {
        return INSTANCE.getMCService(clazz);
    }

    public static MCPluginManager getPluginManager() {
        return pluginManager;
    }
}
