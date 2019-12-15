package org.battleplugins.message;

/**
 * Represents a click action in a message or
 * a book.
 */
public enum ClickAction {

    /**
     * Changes the page of a book
     */
    CHANGE_PAGE,
    /**
     * Opens a file
     *
     * @deprecated internal use only,
     * may only be exposed on modded servers/clients
     */
    @Deprecated
    OPEN_FILE,
    /**
     * Opens a URL
     */
    OPEN_URL,
    /**
     * Runs a command
     */
    RUN_COMMAND,
    /**
     * Suggests a command
     */
    SUGGEST_COMMAND,
}
