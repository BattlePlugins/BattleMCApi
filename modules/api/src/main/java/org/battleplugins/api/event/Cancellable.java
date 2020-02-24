package org.battleplugins.api.event;

/**
 * Represents a cancellable event.
 */
public interface Cancellable {

    /**
     * If the event is cancelled
     *
     * @return if the event is cancelled
     */
    boolean isCancelled();

    /**
     * Cancels the event
     *
     * @param cancelled if the event is cancelled
     */
    void setCancelled(boolean cancelled);
}
