package org.battleplugins.plugin;

import lombok.Getter;

import org.battleplugins.plugin.platform.PlatformPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

@Getter
public class PluginManager {

    private Plugin plugin;

    public void initializePlugin(PlatformPlugin platformPlugin) {
        InputStream propertyStream = getClass().getResourceAsStream("/plugin.properties");
        BufferedReader reader = new BufferedReader(new InputStreamReader(propertyStream));
        try {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.startsWith("main=")) {
                    String className = line.replace("main=", "");
                    Class<?>[] args = {};
                    Class<?> pluginClass = Class.forName(className, true, Plugin.class.getClassLoader());
                    if (Plugin.class.isAssignableFrom(pluginClass))
                        this.plugin = (Plugin) pluginClass.getConstructor(args).newInstance((Object[]) args);
                    else
                        throw new RuntimeException("Could not initialize " + className + " because it did not extend MCPlugin.");

                    if (!pluginClass.isAnnotationPresent(PluginProperties.class))
                        throw new RuntimeException("@PluginProperties annotation is not present!");

                    plugin.setPlatformPlugin(platformPlugin);
                }
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    public void enablePlugin() {
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

    public void disablePlugin() {
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
