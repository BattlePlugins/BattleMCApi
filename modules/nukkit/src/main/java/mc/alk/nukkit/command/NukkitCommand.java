package mc.alk.nukkit.command;

import cn.nukkit.command.Command;

import mc.alk.mc.command.MCCommand;

import java.util.Arrays;
import java.util.List;

public class NukkitCommand extends MCCommand {

    private Command command;

    public NukkitCommand(Command command) {
        this(command.getLabel(), command.getDescription(), command.getPermission(), Arrays.asList(command.getAliases()));

        this.command = command;
    }

    public NukkitCommand(String label, String description, String permission, List<String> aliases) {
        super(label, description, permission, aliases);
    }

    public Command getNukkitCommand() {
        return command;
    }
}
