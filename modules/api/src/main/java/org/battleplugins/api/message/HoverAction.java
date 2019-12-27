package org.battleplugins.api.message;

/**
 * Represents a hover action in a message or
 * a book.
 */
public enum HoverAction {

    /**
     * Shows an achievement
     *
     * @deprecated removed in 1.12
     */
    @Deprecated
    SHOW_ACHIEVEMENT,
    /**
     * Shows an entity
     */
    SHOW_ENTITY,
    /**
     * Shows an item
     */
    SHOW_ITEM,
    /**
     * Shows text
     */
    SHOW_TEXT;
}
