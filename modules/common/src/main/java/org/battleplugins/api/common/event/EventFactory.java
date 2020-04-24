package org.battleplugins.api.common.event;

import lombok.AllArgsConstructor;

import org.battleplugins.api.common.event.gen.EventGenerator;
import org.battleplugins.api.entity.Entity;
import org.battleplugins.api.entity.hand.Hand;
import org.battleplugins.api.event.Cancellable;
import org.battleplugins.api.event.Event;
import org.battleplugins.api.event.player.PlayerInteractBlockEvent;
import org.battleplugins.api.event.player.PlayerInteractEntityEvent;
import org.battleplugins.api.event.player.PlayerInteractItemEvent;
import org.battleplugins.api.event.player.PlayerJoinEvent;
import org.battleplugins.api.event.player.PlayerQuitEvent;
import org.battleplugins.api.entity.living.player.Player;
import org.battleplugins.api.inventory.item.ItemStack;
import org.battleplugins.api.message.Message;
import org.battleplugins.api.world.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    private <T extends Event> T generateEvent(Class<T> eventClass, Map<String, Object> params) {
        return (T) EventGenerator.generate(eventClass).newInstance(params);
    }

    public PlayerJoinEvent firePlayerJoin(Player player, Message joinMessage) {
        Map<String, Object> joinMap = new HashMap<>();
        joinMap.put("player", player);
        joinMap.put("joinMessage", joinMessage);
        PlayerJoinEvent joinEvent = generateEvent(PlayerJoinEvent.class, joinMap);
        this.bus.fire(joinEvent);
        return joinEvent;
    }

    public PlayerQuitEvent firePlayerQuit(Player player, Message quitMessage) {
        Map<String, Object> quitMap = new HashMap<>();
        quitMap.put("player", player);
        quitMap.put("quitMessage", quitMessage);
        PlayerQuitEvent quitEvent = generateEvent(PlayerQuitEvent.class, quitMap);
        this.bus.fire(quitEvent);
        return quitEvent;
    }

    public PlayerInteractEntityEvent firePlayerInteractEntity(Player player, Hand hand, Entity entity, PlayerInteractEntityEvent.Action action, boolean cancelled) {
        Map<String, Object> interactMap = new HashMap<>();
        interactMap.put("player", player);
        interactMap.put("hand", hand);
        interactMap.put("entity", entity);
        interactMap.put("action", action);
        interactMap.put("cancelled", cancelled);
        PlayerInteractEntityEvent interactEntityEvent = generateEvent(PlayerInteractEntityEvent.class, interactMap);
        this.bus.fire(interactEntityEvent);
        return interactEntityEvent;
    }

    public PlayerInteractBlockEvent firePlayerInteractBlock(Player player, Hand hand, Block block, PlayerInteractBlockEvent.Action action, boolean cancelled) {
        Map<String, Object> interactMap = new HashMap<>();
        interactMap.put("player", player);
        interactMap.put("hand", hand);
        interactMap.put("block", block);
        interactMap.put("action", action);
        interactMap.put("cancelled", cancelled);
        PlayerInteractBlockEvent interactBlockEvent = generateEvent(PlayerInteractBlockEvent.class, interactMap);
        this.bus.fire(interactBlockEvent);
        return interactBlockEvent;
    }

    public PlayerInteractItemEvent firePlayerInteractItem(Player player, Hand hand, ItemStack item, PlayerInteractItemEvent.Action action, boolean cancelled) {
        Map<String, Object> interactMap = new HashMap<>();
        interactMap.put("player", player);
        interactMap.put("hand", hand);
        interactMap.put("item", Optional.ofNullable(item));
        interactMap.put("action", action);
        interactMap.put("cancelled", cancelled);
        PlayerInteractItemEvent interactItemEvent = generateEvent(PlayerInteractItemEvent.class, interactMap);
        this.bus.fire(interactItemEvent);
        return interactItemEvent;
    }
}
