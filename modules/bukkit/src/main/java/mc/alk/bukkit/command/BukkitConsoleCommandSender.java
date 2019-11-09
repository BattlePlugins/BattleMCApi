package mc.alk.bukkit.command;

import org.bukkit.command.ConsoleCommandSender;

public class BukkitConsoleCommandSender extends BukkitCommandSender {

    public BukkitConsoleCommandSender(ConsoleCommandSender sender) {
        super(sender);

    }

    @Override
    public boolean isOp() {
        return true;
    }
}
