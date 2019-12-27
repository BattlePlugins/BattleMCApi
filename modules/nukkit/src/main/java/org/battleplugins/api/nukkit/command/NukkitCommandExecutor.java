package org.battleplugins.api.nukkit.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;

import org.battleplugins.api.command.CommandExecutor;
import org.battleplugins.api.nukkit.entity.living.player.NukkitPlayer;

public class NukkitCommandExecutor extends Command {

    private CommandExecutor executor;

    public NukkitCommandExecutor(String command, CommandExecutor executor) {
        super(command);

        this.executor = executor;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        org.battleplugins.api.command.CommandSender mcSender;
        if (sender instanceof ConsoleCommandSender)
            mcSender = new NukkitConsoleCommandSender((ConsoleCommandSender) sender);
        else
            mcSender = new NukkitPlayer((Player) sender);

        return executor.onCommand(mcSender, new NukkitCommand(this), label, args);
    }
}
