package mc.alk.bukkit.command;

import mc.alk.bukkit.BukkitPlayer;
import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.command.MCCommandSender;
import mc.alk.mc.util.MCWrapper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BukkitCommandExecutor extends MCWrapper<MCCommandExecutor> implements CommandExecutor {

    public BukkitCommandExecutor(MCCommandExecutor executor) {
        super(executor);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MCCommandSender mcSender;
        if (sender instanceof ConsoleCommandSender)
            mcSender = new BukkitConsoleCommandSender((ConsoleCommandSender) sender);
        else
            mcSender = new BukkitPlayer((Player) sender);

        return handle.onCommand(mcSender, new BukkitCommand(command), label, args);
    }
}
