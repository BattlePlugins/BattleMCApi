package mc.alk.bukkit.command;

import org.bukkit.command.ConsoleCommandSender;

public class BukkitConsoleCommandSender extends BukkitCommandSender {

    private ConsoleCommandSender sender;

    public BukkitConsoleCommandSender(ConsoleCommandSender sender) {
        super(sender);

        this.sender = sender;
    }

    @Override
    public boolean isOp() {
        return true;
    }
}
