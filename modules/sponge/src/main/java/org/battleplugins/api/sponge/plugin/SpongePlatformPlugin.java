package org.battleplugins.api.sponge.plugin;

import mc.euro.version.Version;

import org.battleplugins.api.command.Command;
import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.plugin.Plugin;
import org.battleplugins.api.plugin.PluginDescription;
import org.battleplugins.api.plugin.platform.PlatformPlugin;
import org.battleplugins.api.sponge.command.SpongeCommandExecutor;
import org.battleplugins.api.sponge.logger.SpongeLogger;
import org.battleplugins.api.util.MCWrapper;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class SpongePlatformPlugin extends MCWrapper<PluginContainer> implements PlatformPlugin {

    public SpongePlatformPlugin(PluginContainer handle) {
        super(handle);
    }

    @Override
    public PluginDescription getPluginDescription() {
        return new PluginDescription() {

            @Override
            public String getId() {
                return handle.getId();
            }

            @Override
            public String getName() {
                return handle.getName();
            }

            @Override
            public Version<Plugin> getVersion() {
                return new Version<>(handle.getVersion().orElse("1.0"));
            }

            @Override
            public Optional<String> getDescription() {
                return handle.getDescription();
            }

            @Override
            public Optional<String> getURL() {
                return handle.getUrl();
            }

            @Override
            public Optional<String[]> getAuthors() {
                return handle.getAuthors().size() == 0 ? Optional.empty() : Optional.of(handle.getAuthors().toArray(new String[0]));
            }
        };
    }

    @Override
    public void registerPluginCommand(Command command, CommandExecutor executor) {
        List<String> aliases = command.getAliases();
        aliases.add(command.getLabel());
        Sponge.getCommandManager().register(this, new SpongeCommandExecutor(executor, command), aliases);
    }

    @Override
    public File getPluginDataFolder() {
        return new File("config/" + handle.getId() + "/");
    }

    @Override
    public SpongeLogger getPluginLogger() {
        return new SpongeLogger(handle.getLogger());
    }

    @Override
    public boolean isPluginEnabled() {
        return Sponge.getPluginManager().isLoaded(handle.getId());
    }
}
