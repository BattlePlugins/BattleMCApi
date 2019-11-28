package org.battleplugins.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Command {

    private String label;
    private String description;
    private String permission;

    private List<String> aliases;
}
