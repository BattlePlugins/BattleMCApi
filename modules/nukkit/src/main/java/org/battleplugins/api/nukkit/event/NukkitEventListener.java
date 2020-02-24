package org.battleplugins.api.nukkit.event;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;

import cn.nukkit.event.player.PlayerQuitEvent;
import lombok.AllArgsConstructor;

import org.battleplugins.api.common.event.EventFactory;
import org.battleplugins.api.message.Message;
import org.battleplugins.api.nukkit.entity.living.player.NukkitPlayer;

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
}
