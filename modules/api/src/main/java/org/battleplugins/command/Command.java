package org.battleplugins.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a command which executes a various task depending on
 * what the user inputted.
 */
@Getter
@Setter
@AllArgsConstructor
public class Command {

    /**
     * The command label
     *
     * @param label the command label
     * @return the command label
     */
    private String label;

    /**
     * The command description
     *
     * @param description the command description
     * @return the command description
     */
    private String description;

    /**
     * The permission needed to run the command
     *
     * @param permission the permission needed to run the command
     * @return the permission needed to run the command
     */
    private String permission;

    /**
     * A list of command aliases
     *
     * @param aliases the command aliases
     * @return a list of command aliases
     */
    private List<String> aliases;
}
