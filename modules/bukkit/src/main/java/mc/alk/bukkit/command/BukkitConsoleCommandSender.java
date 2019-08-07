package mc.alk.bukkit.command;

import org.bukkit.command.CommandSender;

public class BukkitConsoleCommandSender extends BukkitCommandSender {

    public BukkitConsoleCommandSender(CommandSender sender) {
        super(sender);
    }

    @Override
    public boolean isOp() {
        return true;
    }
}
