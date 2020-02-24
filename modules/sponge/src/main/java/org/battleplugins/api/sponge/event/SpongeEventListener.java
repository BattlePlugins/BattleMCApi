package org.battleplugins.api.sponge.event;

import lombok.AllArgsConstructor;

import org.battleplugins.api.common.event.EventFactory;
import org.battleplugins.api.message.Message;
import org.battleplugins.api.sponge.entity.living.player.SpongePlayer;
import org.battleplugins.api.sponge.message.SpongeMessage;
import org.spongepowered.api.event.Listener;
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
}
