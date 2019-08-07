package mc.alk.bukkit.command;

import mc.alk.mc.command.MCCommand;

import org.bukkit.command.Command;

import java.util.List;

public class BukkitCommand implements MCCommand {

    private Command command;

    public BukkitCommand(Command command) {
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
        return command.getAliases();
    }

    public Command getBukkitCommand() {
        return command;
    }
}
