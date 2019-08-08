package mc.alk.mc.command;

import java.util.List;

public class MCCommand {

    private String label;
    private String description;
    private String permission;

    private List<String> aliases;

    public MCCommand(String label, String description, String permission, List<String> aliases) {
        this.label = label;
        this.description = description;
        this.permission = permission;
        this.aliases = aliases;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
}
