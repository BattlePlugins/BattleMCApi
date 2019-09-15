package mc.alk.nukkit.command;

import cn.nukkit.command.ConsoleCommandSender;

public class NukkitConsoleCommandSender extends NukkitCommandSender {

    public NukkitConsoleCommandSender(ConsoleCommandSender sender) {
        super(sender);
    }

    @Override
    public boolean isOp() {
        return true;
    }
}
