package mc.alk.nukkit.command;

import cn.nukkit.command.CommandSender;

public class NukkitConsoleCommandSender extends NukkitCommandSender {

    public NukkitConsoleCommandSender(CommandSender sender) {
        super(sender);
    }

    @Override
    public boolean isOp() {
        return true;
    }
}
