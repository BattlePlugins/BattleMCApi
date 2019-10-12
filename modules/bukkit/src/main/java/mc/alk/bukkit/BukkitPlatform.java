package mc.alk.bukkit;

import mc.alk.bukkit.chat.BukkitMessage;
import mc.alk.bukkit.chat.SpigotMessage;
import mc.alk.bukkit.inventory.BukkitInventory;
import mc.alk.bukkit.inventory.BukkitItemStack;
import mc.alk.mc.APIType;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.MCPlatform;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.chat.Message;
import mc.alk.mc.inventory.MCInventory;
import mc.alk.mc.inventory.MCItemStack;
import mc.alk.mc.plugin.MCPlugin;
import mc.alk.mc.MCWorld;
import mc.alk.mc.plugin.MCServicePriority;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BukkitPlatform extends MCPlatform {

    @Override
    public APIType getAPIType() {
        return APIType.BUKKIT;
    }

    @Override
    public MCLocation getLocation(String world, double x, double y, double z) {
        return new BukkitLocation(world, x, y, z);
    }

    @Override
    public MCLocation getLocation(String world, double x, double y, double z, float pitch, float yaw) {
        return new BukkitLocation(world, x, y, z, pitch, yaw);
    }

    @Override
    public MCWorld getWorld(String world) {
        return new BukkitWorld(Bukkit.getWorld(world));
    }

    @Override
    public long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis) {
        return Bukkit.getScheduler().scheduleSyncDelayedTask((JavaPlugin) plugin.getPlatformPlugin(), runnable,millis/50);
    }

    @Override
    public long scheduleRepeatingTask(MCPlugin plugin, Runnable runnable, long millis) {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask((JavaPlugin) plugin.getPlatformPlugin(), runnable, millis/50, millis/50);
    }

    @Override
    public MCPlayer getPlayer(String name) {
        Player p = Bukkit.getPlayer(name);
        return p == null ? null : new BukkitPlayer(p);
    }

    @Override
    public MCPlayer getPlayer(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        return p == null ? null : new BukkitPlayer(p);
    }

    @Override
    public MCOfflinePlayer getOfflinePlayer(String name) {
        return new BukkitOfflinePlayer(Bukkit.getOfflinePlayer(name));
    }

    @Override
    public MCOfflinePlayer getOfflinePlayer(UUID uuid) {
        return new BukkitOfflinePlayer(Bukkit.getOfflinePlayer(uuid));
    }

    @Override
    public Collection<MCPlayer> getOnlinePlayers() {
        return Bukkit.getOnlinePlayers().stream().map(BukkitPlayer::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<MCOfflinePlayer> getOfflinePlayers() {
        return Stream.of(Bukkit.getOfflinePlayers())
                .map(BukkitOfflinePlayer::new).collect(Collectors.toList());
    }

    @Override
    public boolean isMainThread() {
        return Bukkit.isPrimaryThread();
    }

    @Override
    public boolean isOnlineMode() {
        return Bukkit.getOnlineMode();
    }

    @Override
    public String getVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public Message getDefaultPlatformMessage() {
        if (isSpigot())
            return new SpigotMessage();

        return new BukkitMessage();
    }

    @Override
    public MCItemStack getDefaultPlatformItemStack() {
        return new BukkitItemStack(new ItemStack(Material.AIR));
    }

    @Override
    public MCInventory createInventory(MCPlugin plugin, int slots, String title) {
        return new BukkitInventory(Bukkit.createInventory(null, slots, title));
    }

    @Override
    public <T> void registerService(Class<T> clazz, T service, MCPlugin plugin, MCServicePriority priority) {
        Bukkit.getServicesManager().register(clazz, service, (Plugin) plugin.getPlatformPlugin(), ServicePriority.values()[priority.ordinal()]);
    }

    @Override
    public <T> T getService(Class<T> clazz) {
        return Bukkit.getServicesManager().getRegistration(clazz).getProvider();
    }

    @Override
    public boolean cancelTask(long id) {
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
