package org.battleplugins.api.bukkit.event;

import lombok.AllArgsConstructor;

import org.battleplugins.api.bukkit.entity.BukkitEntity;
import org.battleplugins.api.bukkit.entity.living.player.BukkitPlayer;
import org.battleplugins.api.bukkit.inventory.item.BukkitItemStack;
import org.battleplugins.api.bukkit.world.block.BukkitBlock;
import org.battleplugins.api.common.event.EventFactory;
import org.battleplugins.api.entity.hand.Hand;
import org.battleplugins.api.entity.hand.Hands;
import org.battleplugins.api.event.player.PlayerInteractBlockEvent;
import org.battleplugins.api.event.player.PlayerInteractItemEvent;
import org.battleplugins.api.message.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;

@AllArgsConstructor
public class BukkitEventListener implements Listener {

    private EventFactory factory;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        org.battleplugins.api.event.player.PlayerJoinEvent joinEvent =
                factory.firePlayerJoin(new BukkitPlayer(event.getPlayer()), Message.builder().message(event.getJoinMessage()).build());

        event.setJoinMessage(joinEvent.getJoinMessage().getPlainText());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        org.battleplugins.api.event.player.PlayerQuitEvent quitEvent =
                factory.firePlayerQuit(new BukkitPlayer(event.getPlayer()), Message.builder().message(event.getQuitMessage()).build());

        event.setQuitMessage(quitEvent.getQuitMessage().getPlainText());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        BukkitPlayer player = new BukkitPlayer(event.getPlayer());
        Hand hand = event.getHand() == EquipmentSlot.HAND ? Hands.MAIN_HAND : Hands.OFF_HAND;
        if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            PlayerInteractBlockEvent playerInteractBlockEvent = factory.firePlayerInteractBlock(
                    player,
                    hand,
                    new BukkitBlock(event.getClickedBlock()),
                    event.getAction() == Action.LEFT_CLICK_BLOCK ? PlayerInteractBlockEvent.Action.BREAK : PlayerInteractBlockEvent.Action.PLACE,
                    event.useInteractedBlock() == Event.Result.DENY
            );
            event.setCancelled(playerInteractBlockEvent.isCancelled());
        }
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
            PlayerInteractItemEvent playerInteractItemEvent = factory.firePlayerInteractItem(
                    player,
                    hand,
                    new BukkitItemStack(event.getItem()),
                    event.getAction() == Action.LEFT_CLICK_AIR ? PlayerInteractItemEvent.Action.PRIMARY : PlayerInteractItemEvent.Action.SECONDARY,
                    event.useItemInHand() == Event.Result.DENY
            );
            event.setCancelled(playerInteractItemEvent.isCancelled());
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        BukkitPlayer player = new BukkitPlayer(event.getPlayer());
        Hand hand = event.getHand() == EquipmentSlot.HAND ? Hands.MAIN_HAND : Hands.OFF_HAND;
        org.battleplugins.api.event.player.PlayerInteractEntityEvent playerInteractEntityEvent =
                factory.firePlayerInteractEntity(
                        player,
                        hand,
                        new BukkitEntity<>(event.getRightClicked()),
                        org.battleplugins.api.event.player.PlayerInteractEntityEvent.Action.INTERACT,
                        event.isCancelled()
                );
        event.setCancelled(playerInteractEntityEvent.isCancelled());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        BukkitPlayer player = new BukkitPlayer((Player) event.getDamager());
        org.battleplugins.api.event.player.PlayerInteractEntityEvent playerInteractEntityEvent =
                factory.firePlayerInteractEntity(
                        player,
                        player.getHand(),
                        new BukkitEntity<>(event.getEntity()),
                        org.battleplugins.api.event.player.PlayerInteractEntityEvent.Action.INTERACT,
                        event.isCancelled()
                );
        event.setCancelled(playerInteractEntityEvent.isCancelled());
    }
}
