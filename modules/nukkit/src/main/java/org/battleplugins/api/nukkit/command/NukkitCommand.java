package org.battleplugins.api.nukkit.command;

import cn.nukkit.command.Command;

import java.util.Arrays;
import java.util.List;

public class NukkitCommand extends org.battleplugins.api.command.Command {

    public NukkitCommand(Command command) {
        this(command.getLabel(), command.getDescription(), command.getPermission(), Arrays.asList(command.getAliases()));
    }

    public NukkitCommand(String label, String description, String permission, List<String> aliases) {
        super(label, description, permission, aliases);
    }
}
