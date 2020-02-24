package org.battleplugins.api.common.event;

import lombok.AllArgsConstructor;

import org.battleplugins.api.common.event.gen.EventGenerator;
import org.battleplugins.api.event.Cancellable;
import org.battleplugins.api.event.Event;
import org.battleplugins.api.event.player.PlayerJoinEvent;
import org.battleplugins.api.event.player.PlayerQuitEvent;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.message.Message;

import java.util.LinkedHashMap;
import java.util.function.Supplier;

@AllArgsConstructor
public class EventFactory {

    private AbstractEventBus bus;

    private <T extends Event> void fire(Class<T> eventClass, Supplier<T> supplier) {
        T event = supplier.get();
        if (event instanceof Cancellable && ((Cancellable) event).isCancelled())
            return;

        if (event.isAsync()) {
            // TODO: async event firing
        } else {
            if (!this.bus.shouldFire(eventClass))
                return;

            this.bus.fire(event);
        }
    }

    private <T extends Event> T generateEvent(Class<T> eventClass, LinkedHashMap<String, Object> params) {
        return (T) EventGenerator.generate(eventClass).newInstance(params);
    }

    public PlayerJoinEvent firePlayerJoin(Player player, Message joinMessage) {
        LinkedHashMap<String, Object> joinMap = new LinkedHashMap<>();
        joinMap.put("joinMessage", joinMessage);
        joinMap.put("player", player);
        PlayerJoinEvent joinEvent = generateEvent(PlayerJoinEvent.class, joinMap);
        this.bus.fire(joinEvent);
        return joinEvent;
    }

    public PlayerQuitEvent firePlayerQuit(Player player, Message quitMessage) {
        LinkedHashMap<String, Object> quitMap = new LinkedHashMap<>();
        quitMap.put("quitMessage", quitMessage);
        quitMap.put("player", player);
        PlayerQuitEvent quitEvent = generateEvent(PlayerQuitEvent.class, quitMap);
        this.bus.fire(quitEvent);
        return quitEvent;
    }
}
