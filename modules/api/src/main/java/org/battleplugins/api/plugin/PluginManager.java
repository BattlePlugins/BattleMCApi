package org.battleplugins.api.plugin;

import lombok.Getter;

import org.battleplugins.api.plugin.platform.PlatformPlugin;

import java.util.List;

/**
 * The plugin manager for managing plugins built against
 * this API (not to be confused with the platform plugin manager).
 */
public interface PluginManager {

    /**
     * The plugins loaded into this API
     *
     * @return the plugins loaded into this API
     */
    List<Plugin> getPlugins();

    /**
     * Initializes the given {@link PlatformPlugin}
     *
     * @param platformPlugin the plugin to initialize
     * @return the {@link Plugin} from this platform plugin
     */
    Plugin initializePlugin(PlatformPlugin platformPlugin);

    /**
     * Enables the plugin
     *
     * @param plugin the plugin to enable
     */
    void enablePlugin(Plugin plugin);

    /**
     * Disables the plugin
     *
     * @param plugin the plugin to disable
     */
    void disablePlugin(Plugin plugin);

    /**
     * The plugins loaded into the platform this
     * API is running on
     *
     * @return the plugins loaded on the platform
     */
    List<PlatformPlugin> getPlatformPlugins();
}
