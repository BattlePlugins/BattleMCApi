package org.battleplugins.api.bukkit.command;

import org.battleplugins.api.bukkit.entity.living.player.BukkitPlayer;

import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.util.MCWrapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BukkitCommandExecutor extends MCWrapper<CommandExecutor> implements org.bukkit.command.CommandExecutor {

    public BukkitCommandExecutor(CommandExecutor executor) {
        super(executor);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        org.battleplugins.api.command.CommandSender mcSender;
        if (sender instanceof ConsoleCommandSender)
            mcSender = new BukkitConsoleCommandSender((ConsoleCommandSender) sender);
        else
            mcSender = new BukkitPlayer((Player) sender);

        return handle.onCommand(mcSender, new BukkitCommand(command), label, args);
    }
}
