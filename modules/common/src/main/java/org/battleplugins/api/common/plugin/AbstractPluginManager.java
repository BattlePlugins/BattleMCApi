package org.battleplugins.api.common.plugin;

import lombok.Getter;

import org.battleplugins.api.common.event.AbstractEventBus;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.PluginManager;
import org.battleplugins.api.plugin.PluginProperties;
import org.battleplugins.api.plugin.platform.PlatformPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPluginManager implements PluginManager {

    @Getter
    private List<Plugin> plugins = new ArrayList<>();

    @Override
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

    @Override
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
            this.registerEvents(plugin);
        } else {
            throw new RuntimeException("Plugin is not yet initialized!");
        }
    }

    @Override
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

    private void registerEvents(Plugin plugin) {
        InputStream eventStream = getClass().getResourceAsStream("/event.properties");
        if (eventStream == null)
            return; // no event file, means plugin has no events

        BufferedReader reader = new BufferedReader(new InputStreamReader(eventStream));
        try {
            while (reader.ready()) {
                String line = reader.readLine();
                Class<?> eventHolderClass;
                try {
                    eventHolderClass = Class.forName(line);
                } catch (ClassNotFoundException ex) {
                    plugin.getLogger().warning("Failed to find event class " + line + "! Please contact the author of " + plugin.getDescription().getName() + " as this is an issue with their plugin!");
                    continue;
                }

                AbstractEventBus eventBus = (AbstractEventBus) plugin.getEventBus();
                eventBus.register(plugin, eventHolderClass, true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
