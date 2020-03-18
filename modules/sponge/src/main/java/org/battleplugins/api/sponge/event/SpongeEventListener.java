package org.battleplugins.api.sponge.event;

import lombok.AllArgsConstructor;

import org.battleplugins.api.common.event.EventFactory;
import org.battleplugins.api.entity.hand.Hand;
import org.battleplugins.api.entity.hand.Hands;
import org.battleplugins.api.event.player.PlayerInteractBlockEvent;
import org.battleplugins.api.event.player.PlayerInteractEntityEvent;
import org.battleplugins.api.event.player.PlayerInteractItemEvent;
import org.battleplugins.api.message.Message;
import org.battleplugins.api.sponge.entity.SpongeEntity;
import org.battleplugins.api.sponge.entity.living.player.SpongePlayer;
import org.battleplugins.api.sponge.inventory.item.SpongeItemStack;
import org.battleplugins.api.sponge.message.SpongeMessage;
import org.battleplugins.api.sponge.world.block.SpongeBlock;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;

@AllArgsConstructor
public class SpongeEventListener {

    private EventFactory factory;

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event) {
        org.battleplugins.api.event.player.PlayerJoinEvent joinEvent =
                factory.firePlayerJoin(new SpongePlayer(event.getTargetEntity()), Message.builder().message(event.getMessage().toPlain()).build());

        event.setMessage(((SpongeMessage) joinEvent.getJoinMessage()).getMessage());
    }

    @Listener
    public void onPlayerQuit(ClientConnectionEvent.Disconnect event) {
        org.battleplugins.api.event.player.PlayerQuitEvent quitEvent =
                factory.firePlayerQuit(new SpongePlayer(event.getTargetEntity()), Message.builder().message(event.getMessage().toPlain()).build());

        event.setMessage(((SpongeMessage) quitEvent.getQuitMessage()).getMessage());
    }

    @Listener
    public void onInteractBlockBreak(InteractBlockEvent.Primary event, @First Player player) {
        SpongePlayer spongePlayer = new SpongePlayer(player);
        Hand hand = event.getContext().get(EventContextKeys.USED_HAND).orElse(HandTypes.MAIN_HAND) == HandTypes.MAIN_HAND ? Hands.MAIN_HAND : Hands.OFF_HAND;
        PlayerInteractBlockEvent playerInteractBlockEvent = factory.firePlayerInteractBlock(
                spongePlayer,
                hand,
                new SpongeBlock(event.getTargetBlock()),
                PlayerInteractBlockEvent.Action.BREAK
        );
        event.setCancelled(playerInteractBlockEvent.isCancelled());
    }

    @Listener
    public void onInteractBlockPlace(InteractBlockEvent.Secondary event, @First Player player) {
        SpongePlayer spongePlayer = new SpongePlayer(player);
        Hand hand = event.getHandType() == HandTypes.MAIN_HAND ? Hands.MAIN_HAND : Hands.OFF_HAND;
        PlayerInteractBlockEvent playerInteractBlockEvent = factory.firePlayerInteractBlock(
                spongePlayer,
                hand,
                new SpongeBlock(event.getTargetBlock()),
                PlayerInteractBlockEvent.Action.PLACE
        );
        event.setCancelled(playerInteractBlockEvent.isCancelled());
    }

    @Listener
    public void onInteractItemPrimary(InteractItemEvent.Primary event, @First Player player) {
        SpongePlayer spongePlayer = new SpongePlayer(player);
        Hand hand = event.getHandType() == HandTypes.MAIN_HAND ? Hands.MAIN_HAND : Hands.OFF_HAND;
        PlayerInteractItemEvent playerInteractItemEvent = factory.firePlayerInteractItem(
                spongePlayer,
                hand,
                new SpongeItemStack(event.getItemStack().createStack()),
                PlayerInteractItemEvent.Action.PRIMARY
        );
        event.setCancelled(playerInteractItemEvent.isCancelled());
    }

    @Listener
    public void onInteractItemSecondary(InteractItemEvent.Secondary event, @First Player player) {
        SpongePlayer spongePlayer = new SpongePlayer(player);
        Hand hand = event.getHandType() == HandTypes.MAIN_HAND ? Hands.MAIN_HAND : Hands.OFF_HAND;
        PlayerInteractItemEvent playerInteractItemEvent = factory.firePlayerInteractItem(
                spongePlayer,
                hand,
                new SpongeItemStack(event.getItemStack().createStack()),
                PlayerInteractItemEvent.Action.SECONDARY
        );
        event.setCancelled(playerInteractItemEvent.isCancelled());
    }

    @Listener
    public void onInteractEntityPrimary(InteractEntityEvent.Primary event, @First Player player) {
        SpongePlayer spongePlayer = new SpongePlayer(player);
        Hand hand = event.getHandType() == HandTypes.MAIN_HAND ? Hands.MAIN_HAND : Hands.OFF_HAND;
        PlayerInteractEntityEvent playerInteractEntityEvent = factory.firePlayerInteractEntity(
                spongePlayer,
                hand,
                new SpongeEntity<>(event.getTargetEntity()),
                PlayerInteractEntityEvent.Action.ATTACK
        );
        event.setCancelled(playerInteractEntityEvent.isCancelled());
    }

    @Listener
    public void onInteractEntitySecondary(InteractEntityEvent.Secondary event, @First Player player) {
        SpongePlayer spongePlayer = new SpongePlayer(player);
        Hand hand = event.getHandType() == HandTypes.MAIN_HAND ? Hands.MAIN_HAND : Hands.OFF_HAND;
        PlayerInteractEntityEvent playerInteractEntityEvent = factory.firePlayerInteractEntity(
                spongePlayer,
                hand,
                new SpongeEntity<>(event.getTargetEntity()),
                PlayerInteractEntityEvent.Action.INTERACT
        );
        event.setCancelled(playerInteractEntityEvent.isCancelled());
    }
}
