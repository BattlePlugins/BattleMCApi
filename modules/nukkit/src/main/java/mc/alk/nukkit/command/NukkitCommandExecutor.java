package mc.alk.nukkit.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;

import mc.alk.mc.command.MCCommandExecutor;
import mc.alk.mc.command.MCCommandSender;
import mc.alk.nukkit.NukkitPlayer;

public class NukkitCommandExecutor extends Command {

    private MCCommandExecutor executor;

    public NukkitCommandExecutor(String command, MCCommandExecutor executor) {
        super(command);

        this.executor = executor;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        MCCommandSender mcSender = null;
        if (sender instanceof ConsoleCommandSender)
            mcSender = new NukkitConsoleCommandSender(sender);
        else
            mcSender = new NukkitPlayer((Player) sender);

        return executor.onCommand(mcSender, new NukkitCommand(this), label, args);
    }
}
