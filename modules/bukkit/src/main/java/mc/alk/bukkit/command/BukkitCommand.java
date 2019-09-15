package mc.alk.bukkit.command;

import mc.alk.mc.command.MCCommand;

import org.bukkit.command.Command;

import java.util.List;

public class BukkitCommand extends MCCommand {

    private Command command;

    public BukkitCommand(Command command) {
        this(command.getLabel(), command.getDescription(), command.getPermission(), command.getAliases());

        this.command = command;
    }

    public BukkitCommand(String label, String description, String permission, List<String> aliases) {
        super(label, description, permission, aliases);
    }
}
