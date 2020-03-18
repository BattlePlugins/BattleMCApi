package org.battleplugins.api.nukkit.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.player.PlayerInteractEntityEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;

import lombok.AllArgsConstructor;

import org.battleplugins.api.common.event.EventFactory;
import org.battleplugins.api.entity.hand.Hands;
import org.battleplugins.api.event.player.PlayerInteractBlockEvent;
import org.battleplugins.api.event.player.PlayerInteractItemEvent;
import org.battleplugins.api.message.Message;
import org.battleplugins.api.nukkit.entity.NukkitEntity;
import org.battleplugins.api.nukkit.entity.living.player.NukkitPlayer;
import org.battleplugins.api.nukkit.inventory.item.NukkitItemStack;
import org.battleplugins.api.nukkit.world.block.NukkitBlock;

@AllArgsConstructor
public class NukkitEventListener implements Listener {

    private EventFactory factory;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        org.battleplugins.api.event.player.PlayerJoinEvent joinEvent =
                factory.firePlayerJoin(new NukkitPlayer(event.getPlayer()), Message.builder().message(event.getJoinMessage().getText()).build());

        event.setJoinMessage(joinEvent.getJoinMessage().getPlainText());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        org.battleplugins.api.event.player.PlayerQuitEvent quitEvent =
                factory.firePlayerQuit(new NukkitPlayer(event.getPlayer()), Message.builder().message(event.getQuitMessage().getText()).build());

        event.setQuitMessage(quitEvent.getQuitMessage().getPlainText());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        NukkitPlayer player = new NukkitPlayer(event.getPlayer());
        if (event.getAction() == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK || event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            PlayerInteractBlockEvent playerInteractBlockEvent = factory.firePlayerInteractBlock(
                    player,
                    Hands.MAIN_HAND,
                    new NukkitBlock(event.getBlock()),
                    event.getAction() == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK ? PlayerInteractBlockEvent.Action.BREAK : PlayerInteractBlockEvent.Action.PLACE
            );
            event.setCancelled(playerInteractBlockEvent.isCancelled());
        }
        if (event.getAction() == PlayerInteractEvent.Action.LEFT_CLICK_AIR || event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
            PlayerInteractItemEvent playerInteractItemEvent = factory.firePlayerInteractItem(
                    player,
                    Hands.MAIN_HAND,
                    new NukkitItemStack(event.getItem()),
                    event.getAction() == PlayerInteractEvent.Action.LEFT_CLICK_AIR ? PlayerInteractItemEvent.Action.PRIMARY : PlayerInteractItemEvent.Action.SECONDARY
            );
            event.setCancelled(playerInteractItemEvent.isCancelled());
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        NukkitPlayer player = new NukkitPlayer(event.getPlayer());
        org.battleplugins.api.event.player.PlayerInteractEntityEvent playerInteractEntityEvent =
                factory.firePlayerInteractEntity(
                        player,
                        Hands.MAIN_HAND,
                        new NukkitEntity<>(event.getEntity()),
                        org.battleplugins.api.event.player.PlayerInteractEntityEvent.Action.INTERACT
                );
        event.setCancelled(playerInteractEntityEvent.isCancelled());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        NukkitPlayer player = new NukkitPlayer((Player) event.getDamager());
        org.battleplugins.api.event.player.PlayerInteractEntityEvent playerInteractEntityEvent =
                factory.firePlayerInteractEntity(
                        player,
                        player.getHand(),
                        new NukkitEntity<>(event.getEntity()),
                        org.battleplugins.api.event.player.PlayerInteractEntityEvent.Action.INTERACT
                );
        event.setCancelled(playerInteractEntityEvent.isCancelled());
    }
}
