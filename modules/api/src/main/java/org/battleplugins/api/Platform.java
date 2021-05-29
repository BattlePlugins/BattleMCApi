package org.battleplugins.api;

import mc.euro.version.Version;

import org.battleplugins.api.event.EventBus;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.PluginManager;

/**
 * Represents a platform. Can be extended by any of the
 * platforms in {@link PlatformType}.
 */
public abstract class Platform {

    private static Platform INSTANCE;
    private static EventBus EVENT_BUS;
    private static PluginManager PLUGIN_MANAGER;

    /**
     * Sets the instance of this platform
     *
     * @param platform the platform instance
     */
    public static void setInstance(Platform platform, EventBus bus, PluginManager pluginManager) {
        if (INSTANCE == null) {
            INSTANCE = platform;
        }
        if (EVENT_BUS == null) {
            EVENT_BUS = bus;
        }

        if (PLUGIN_MANAGER == null) {
            PLUGIN_MANAGER = pluginManager;
        }
    }

    /**
     * The {@link PlatformType}
     *
     * @return the platform type
     */
    public abstract PlatformType getType();

    // TODO: Rewrite scheduler/task API
    public abstract long scheduleSyncTask(Plugin plugin, Runnable runnable, long millis);

    // TODO: Rewrite scheduler/task API
    public abstract long scheduleRepeatingTask(Plugin plugin, Runnable runnable, long millis);

    // TODO: Rewrite scheduler/task API
    public abstract boolean cancelTask(long id);

    /**
     * The version of the platform
     *
     * @return the version of the platform
     */
    public abstract Version<Platform> getVersion();

    /**
     * The default {@link ItemStack} used for this platform
     *
     * @return the default ItemStack for this platform
     */
    public abstract ItemStack getDefaultPlatformItemStack();

    protected abstract Registry getPlatformRegistry();

    protected abstract Server getPlatformServer();

    /**
     * The {@link PluginManager}
     *
     * @return the plugin manager
     */
    public static PluginManager getPluginManager() {
        return PLUGIN_MANAGER;
    }

    /**
     * The {@link EventBus}
     *
     * @return the event bus
     */
    public static EventBus getEventBus() {
        return EVENT_BUS;
    }

    /**
     * The {@link Registry}
     *
     * @return the registry
     */
    public static Registry getRegistry() {
        return INSTANCE.getPlatformRegistry();
    }

    /**
     * The {@link Server}
     *
     * @return the server
     */
    public static Server getServer() {
        return INSTANCE.getPlatformServer();
    }

    /**
     * The {@link PlatformType}
     *
     * @return the platform type
     */
    public static PlatformType getPlatformType() {
        return INSTANCE.getType();
    }

    /**
     * The platform in use
     *
     * @return the platform in use
     */
    public static Platform getPlatform() {
        return INSTANCE;
    }
}
