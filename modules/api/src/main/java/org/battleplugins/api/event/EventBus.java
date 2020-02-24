package org.battleplugins.api.event;

import org.battleplugins.api.plugin.Plugin;

import java.util.*;
import java.util.function.Consumer;

/**
 * Represents a bus capable of subscribing
 * or "listening" to events and firing them.
 */
public interface EventBus {

    /**
     * Subscribes to the given event see {@link EventSubscription}
     *
     * @param eventClass the class of the event
     * @param consumer the consumer for handling the event
     * @param <T> the event class
     * @return the event subscription
     */
    <T extends Event> EventSubscription<T> subscribe(Class<T> eventClass, Consumer<? super T> consumer);

    /**
     * Subscribes to the given event see {@link EventSubscription}
     *
     * The difference between this method and {@link #subscribe(Class, Consumer)}
     * is that this method takes in a plugin parameter which allows for
     * the event to be unsubscribed upon plugin disable and reloads.
     *
     * @param plugin the plugin to subscribe the event to
     * @param eventClass the class of the event
     * @param consumer the consumer for handling the event
     * @param <T> the event class
     * @return the event subscription
     */
    <T extends Event> EventSubscription<T> subscribe(Plugin plugin, Class<T> eventClass, Consumer<? super T> consumer);

    /**
     * Unsubscribes the given {@link EventSubscription}
     *
     * @param subscription the event subscription
     */
    <U extends Event> void unsubscribe(EventSubscription<U> subscription);

    /**
     * Provides an instance of the given event holder
     * class for event registration. If your event class
     * contains an empty default constructor, then this
     * method should not need to be called whatsoever.
     *
     * If you have a custom constructor with parameters
     * passed and it's crucial that it remains, see
     * {@link Subscribe#autoIdentifiable()}.
     *
     * @param holderClass the class holding the events
     * @param holder the instance of the holding class
     * @param <T> the value
     */
    <T> void provideInstance(Class<T> holderClass, T holder);

    /**
     * Registers events for the given listener
     *
     * @param plugin the plugin registering the event
     * @param eventHolder the listener
     */
    void register(Plugin plugin, Object eventHolder);

    /**
     * Registers events for the given listener class IF a
     * default constructor is within the class or an instance
     * is provided using {@link #provideInstance(Class, Object)};
     *
     * @param plugin the plugin registering the event
     * @param eventHolderClass the listener class
     */
    void register(Plugin plugin, Class<?> eventHolderClass);

    /**
     * Fires the given {@link Event}
     *
     * @param event the event to fire
     *
     * @return true if the event successfully fired
     */
    boolean fire(Event event);

    /**
     * Gets the subscriptions for the given event class
     *
     * @param eventClass the event class
     * @param <T> the value
     * @return the subscriptions for the event class
     */
    <T extends Event> Set<EventSubscription<T>> getSubscriptions(Class<T> eventClass);
}
