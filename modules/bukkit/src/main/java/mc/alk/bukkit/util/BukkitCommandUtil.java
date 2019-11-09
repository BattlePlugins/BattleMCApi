package mc.alk.bukkit.util;

import mc.alk.mc.command.MCCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

public class BukkitCommandUtil {

    public static class BattleBukkitCommand extends Command implements PluginIdentifiableCommand {

        private CommandExecutor executor;
        private Plugin plugin;

        public BattleBukkitCommand(MCCommand command, Plugin plugin, CommandExecutor executor) {
            super(command.getLabel(), command.getDescription(), "/" + command.getLabel(), command.getAliases());

            this.executor = executor;
            this.plugin = plugin;
        }

        @Override
        public boolean execute(CommandSender sender, String label, String[] aliases) {
            return executor.onCommand(sender, this, label, aliases);
        }

        @Override
        public Plugin getPlugin() {
            return plugin;
        }
    }

    public static void registerCommand(String prefix, Command command) {
        getCommandMap().register(prefix, command);
    }

    public static CommandMap getCommandMap() {
        Field field;
        try {
            field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            return (CommandMap) field.get(Bukkit.getServer());
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
