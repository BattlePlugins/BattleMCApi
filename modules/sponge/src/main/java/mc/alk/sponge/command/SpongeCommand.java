package mc.alk.sponge.command;

import mc.alk.mc.command.MCCommand;

import org.spongepowered.api.command.spec.CommandSpec;

import java.util.List;

public class SpongeCommand implements MCCommand {

    private CommandSpec command;

    private String label;
    private String permission;
    private List<String> aliases;

    public SpongeCommand(CommandSpec command, String label, String permission, List<String> aliases) {
        this.command = command;
        this.label = label;
        this.permission = permission;
        this.aliases = aliases;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getDescription() {
        return command.getShortDescription(null).get().toPlain();
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    public CommandSpec getSpongeCommand() {
        return command;
    }
}
