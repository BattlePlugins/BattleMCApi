package org.battleplugins.api.event;

import org.battleplugins.api.PlatformType;
import org.battleplugins.api.PlatformTypes;

/**
 * Represents an event.
 */
public interface Event {

    /**
     * The available platforms this event is
     * capable of running on
     *
     * @return the available platforms for this event
     */
    default PlatformType[] getAvailablePlatforms() {
        return PlatformTypes.values().clone();
    }

    /**
     * If the event is async
     *
     * @return if the event is async
     */
    default boolean isAsync() {
        return false;
    }
}
