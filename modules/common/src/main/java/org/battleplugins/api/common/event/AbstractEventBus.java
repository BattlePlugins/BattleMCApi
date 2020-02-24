package org.battleplugins.api.common.event;

import lombok.RequiredArgsConstructor;

import net.kyori.event.SimpleEventBus;

import org.battleplugins.api.event.Event;
import org.battleplugins.api.event.EventBus;
import org.battleplugins.api.event.EventSubscription;
import org.battleplugins.api.event.Subscribe;
import org.battleplugins.api.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AbstractEventBus implements EventBus {

    private Map<Class<?>, Object> providedEventHolders = new HashMap<>();

    private SimpleEventBus<Event> bus = new SimpleEventBus<>(Event.class);

    @Override
    public <T extends Event> EventSubscription<T> subscribe(Class<T> eventClass, Consumer<? super T> consumer) {
        return this.subscribe(eventClass, consumer, null, Subscribe.Priority.NORMAL);
    }

    @Override
    public <T extends Event> EventSubscription<T> subscribe(Plugin plugin, Class<T> eventClass, Consumer<? super T> consumer) {
        return this.subscribe(eventClass, consumer, plugin, Subscribe.Priority.NORMAL);
    }

    @Override
    public <U extends Event> void unsubscribe(EventSubscription<U> subscription) {
        this.bus.unregister(subscription);
    }

    @Override
    public <T> void provideInstance(Class<T> holderClass, T holder) {
        this.providedEventHolders.put(holderClass, holder);
    }

    @Override
    public void register(Plugin plugin, Object eventHolder) {
        this.register(plugin, eventHolder, false);
    }

    private void register(Plugin plugin, Object eventHolder, boolean registrationPhase) {
        for (Method method : eventHolder.getClass().getMethods()) {
            if (!method.isAnnotationPresent(Subscribe.class))
                continue;

            if (method.getParameterCount() > 1)
                continue;

            if (!Event.class.isAssignableFrom(method.getParameters()[0].getType()))
                continue;

            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if (registrationPhase && !subscribe.autoIdentifiable())
                continue;

            this.subscribe((Class<? extends Event>) method.getParameters()[0].getType(), (event) -> {
                try {
                    method.invoke(eventHolder, event);
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
            }, plugin, subscribe.priority());
        }
    }

    @Override
    public void register(Plugin plugin, Class<?> eventHolderClass) {
        this.register(plugin, eventHolderClass, false);
    }

    public void register(Plugin plugin, Class<?> eventHolderClass, boolean registrationPhase) {
        for (Method method : eventHolderClass.getMethods()) {
            if (!method.isAnnotationPresent(Subscribe.class))
                continue;

            if (method.getParameterCount() > 1)
                continue;

            if (!Event.class.isAssignableFrom(method.getParameters()[0].getType()))
                continue;

            boolean hasDefaultConstructor = false;
            for (Constructor<?> constructor : eventHolderClass.getDeclaredConstructors()) {
                if (constructor.getParameterTypes().length == 0) {
                    hasDefaultConstructor = true;
                    break;
                }
            }

            if (!hasDefaultConstructor) {
                if (!providedEventHolders.containsKey(eventHolderClass)) {
                    plugin.getLogger().warning("Failed to register events for event holder " + eventHolderClass + " for " + plugin.getDescription().getName() + " since a default constructor was not found. Please contact the author of this plugin!");
                    return;
                } else {
                    this.register(plugin, providedEventHolders.get(eventHolderClass), registrationPhase);
                }
            } else {
                try {
                    this.register(plugin, eventHolderClass.newInstance(), registrationPhase);
                } catch (InstantiationException | IllegalAccessException ex) {
                    plugin.getLogger().warning("Failed to register events for " + eventHolderClass + " for plugin " + plugin.getDescription().getName() + ". Please contact the author of this plugin!");
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean fire(Event event) {
        return this.bus.post(event).wasSuccessful();
    }

    @Override
    public <T extends Event> Set<EventSubscription<T>> getSubscriptions(Class<T> eventClass) {
        return bus.subscribers().values()
                .stream()
                .filter(sub -> sub instanceof EventSubscription && ((EventSubscription<?>) sub).getEventClass().isAssignableFrom(eventClass))
                .map(sub -> ((EventSubscription<T>) sub))
                .collect(Collectors.toSet());
    }

    boolean shouldFire(Class<? extends Event> eventClass) {
        return this.bus.hasSubscribers(eventClass);
    }

    private <T extends Event> EventSubscription<T> subscribe(Class<T> eventClass, Consumer<? super T> handler, Plugin plugin, Subscribe.Priority priority) {
        if (!eventClass.isInterface()) {
            throw new IllegalArgumentException("Event class" + eventClass + " is not an interface!");
        }

        EventSubscription<T> eventSubscription = new EventSubscription<>(this, eventClass, handler, plugin, priority);
        this.bus.register(eventClass, eventSubscription);
        return eventSubscription;
    }
}
