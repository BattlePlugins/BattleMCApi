package org.battleplugins.api.plugin;

import lombok.Getter;

import org.battleplugins.api.plugin.platform.PlatformPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * The plugin manager for managing plugins built against
 * this API (not to be confused with the platform plugin manager).
 */
@Getter
public class PluginManager {

    /**
     * The plugins loaded into this API
     *
     * @return the plugins loaded into this API
     */
    private List<Plugin> plugins = new ArrayList<>();

    /**
     * Initializes the given {@link PlatformPlugin}
     *
     * @param platformPlugin the plugin to initialize
     * @return the {@link Plugin} from this platform plugin
     */
    public Plugin initializePlugin(PlatformPlugin platformPlugin) {
        InputStream propertyStream = getClass().getResourceAsStream("/plugin.properties");
        BufferedReader reader = new BufferedReader(new InputStreamReader(propertyStream));
        try {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.startsWith("main=")) {
                    String className = line.replace("main=", "");
                    Class<?>[] args = {};
                    Class<?> pluginClass = Class.forName(className, true, Plugin.class.getClassLoader());
                    if (!pluginClass.isAnnotationPresent(PluginProperties.class))
                        throw new RuntimeException("@PluginProperties annotation is not present!");

                    if (Plugin.class.isAssignableFrom(pluginClass)) {
                        Plugin plugin = (Plugin) pluginClass.getConstructor(args).newInstance((Object[]) args);
                        this.plugins.add(plugin);

                        plugin.setPlatformPlugin(platformPlugin);
                        return plugin;
                    } else {
                        throw new RuntimeException("Could not initialize " + className + " because it did not extend MCPlugin.");
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Enables the plugin
     *
     * @param plugin the plugin to enable
     */
    public void enablePlugin(Plugin plugin) {
        if (plugin != null) {
            try {
                plugin.onEnable();
            } catch (Throwable ex) {
                System.err.println("Could not enable plugin " + plugin.getDescription().getName() + "!");
                ex.printStackTrace();
                return;
            }
            plugin.getPlatformCode().onEnable();
        } else {
            throw new RuntimeException("Plugin is not yet initialized!");
        }
    }

    /**
     * Disables the plugin
     *
     * @param plugin the plugin to disable
     */
    public void disablePlugin(Plugin plugin) {
        if (plugin != null) {
            try {
                plugin.onDisable();
            } catch (Throwable ex) {
                System.err.println("Could not disable plugin " + plugin.getDescription().getName() + "!");
                ex.printStackTrace();
            }
            plugin.getPlatformCode().onDisable();
        } else {
            throw new RuntimeException("Plugin is not initialized!");
        }
    }
}
