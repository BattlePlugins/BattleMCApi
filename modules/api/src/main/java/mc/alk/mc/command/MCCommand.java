package mc.alk.mc.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MCCommand {

    private String label;
    private String description;
    private String permission;

    private List<String> aliases;
}
