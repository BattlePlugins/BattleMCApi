package org.battleplugins.api.bukkit.event;

import lombok.AllArgsConstructor;

import org.battleplugins.api.bukkit.entity.living.player.BukkitPlayer;
import org.battleplugins.api.common.event.EventFactory;
import org.battleplugins.api.message.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
}
