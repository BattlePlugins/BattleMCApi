package mc.alk.bukkit.command;

import mc.alk.bukkit.BukkitPlayer;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.command.MCCommandSender;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BukkitCommandExecutor implements CommandExecutor {

    private MCCommandExecutor executor;

    public BukkitCommandExecutor(MCCommandExecutor executor) {
        this.executor = executor;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MCCommandSender mcSender = null;
        if (sender instanceof ConsoleCommandSender)
            mcSender = new BukkitConsoleCommandSender(sender);
        else
            mcSender = new BukkitPlayer((Player) sender);

        return executor.onCommand(mcSender, new BukkitCommand(command), label, args);
    }
}
