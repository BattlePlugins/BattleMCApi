package mc.alk.mc.command;

import java.util.List;

public interface MCCommand {

    String getLabel();
    String getDescription();
    String getPermission();

    List<String> getAliases();
}
