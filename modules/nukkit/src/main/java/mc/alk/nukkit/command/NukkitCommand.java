package mc.alk.nukkit.command;

import cn.nukkit.command.Command;

import mc.alk.mc.command.MCCommand;

import java.util.Arrays;
import java.util.List;

public class NukkitCommand implements MCCommand {

    private Command command;

    public NukkitCommand(Command command) {
        this.command = command;
    }

    @Override
    public String getLabel() {
        return command.getLabel();
    }

    @Override
    public String getDescription() {
        return command.getDescription();
    }

    @Override
    public String getPermission() {
        return command.getPermission();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(command.getAliases());
    }

    public Command getNukkitCommand() {
        return command;
    }
}
