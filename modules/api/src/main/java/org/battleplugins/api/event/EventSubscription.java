package org.battleplugins.api.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.kyori.event.EventSubscriber;

import org.battleplugins.api.plugin.Plugin;

import java.util.function.Consumer;

/**
 * Represents a subscribed listener to a
 * {@link Event}. Wraps around the event and
 * is capable of unsubscribing from the
 * event or give information about it.
 *
 * @param <T> the class of the event
 */
@Getter
@RequiredArgsConstructor
public class EventSubscription<T extends Event> implements EventSubscriber<T> {

    /**
     * The event bus.
     */
    @Getter(AccessLevel.NONE)
    private final EventBus eventBus;

    /**
     * The event class
     *
     * @return the event class
     */
    private final Class<T> eventClass;

    /**
     * The consumer responsible for handling
     * this event
     *
     * @return the consumer for handling this event
     */
    private final Consumer<? super T> consumer;

    /**
     * The plugin that owns this event
     *
     * @return the plugin that owns this event
     */
    private final Plugin plugin;

    /**
     * The event priority
     */
    private final Subscribe.Priority priority;

    /**
     * If this event listener is active
     *
     * @return if this event listener is active
     */
    private boolean active;

    /**
     * Unsubscribes from this event listener
     */
    public void unsubscribe() {
        if (!this.active) {
            return;
        }

        this.active = false;
        this.eventBus.unsubscribe(this);
    }

    @Override
    public void invoke(T event) throws Throwable {
        try {
            this.consumer.accept(event);
        } catch (Throwable ex) {
            this.plugin.getLogger().warning("Unable to fire event " + event.getClass().getSimpleName() + " to subscription " + this.consumer.getClass().getSimpleName());
            ex.printStackTrace();
        }
    }

    @Override
    public int postOrder() {
        return priority.postOrder;
    }
}
