package mc.alk.mc;

import mc.alk.mc.chat.Message;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.mc.plugin.MCPluginManager;
import mc.alk.mc.plugin.MCServicePriority;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public abstract class MCPlatform {

    private static MCPlatform INSTANCE;

    private static MCPluginManager pluginManager = new MCPluginManager();

    public static void setInstance(MCPlatform api){
        if (INSTANCE == null){
            INSTANCE = api;
        }
    }

    public abstract MCLocation getLocation(String world, double x, double y, double z);
    public abstract MCLocation getLocation(String world, double x, double y, double z, float pitch, float yaw);

    public abstract Optional<? extends MCWorld> getWorld(String world);

    public abstract APIType getAPIType();

    public abstract long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis);
    public abstract long scheduleRepeatingTask(MCPlugin plugin, Runnable runnable, long millis);

    public abstract boolean cancelTask(long id);

    public abstract Optional<? extends MCPlayer> getPlayer(String name);
    public abstract Optional<? extends MCPlayer> getPlayer(UUID uuid);

    public abstract Optional<? extends MCOfflinePlayer> getOfflinePlayer(String name);

    public abstract Optional<? extends MCOfflinePlayer> getOfflinePlayer(UUID uuid);

    public abstract Collection<? extends MCPlayer> getOnlinePlayers();

    public abstract Collection<? extends MCOfflinePlayer> getOfflinePlayers();

    public abstract boolean isMainThread();

    public abstract boolean isOnlineMode();

    public abstract String getVersion();

    public abstract Message getDefaultPlatformMessage();

    public abstract MCItemStack getDefaultPlatformItemStack();

    public static void broadcastMessage(Message message) {
        INSTANCE.getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }

    public abstract MCInventory createInventory(MCPlugin plugin, int slots, String title);

    public abstract <T> void registerService(Class<T> clazz, T service, MCPlugin plugin, MCServicePriority priority);

    public abstract <T> Optional<T> getService(Class<T> clazz);

    public static MCPluginManager getPluginManager() {
        return pluginManager;
    }

    public static APIType getAPI() {
        return INSTANCE.getAPIType();
    }

    public static MCPlatform getPlatform() {
        return INSTANCE;
    }
}
