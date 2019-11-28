package org.battleplugins.nukkit.inventory.virtual;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryTransactionEvent;
import cn.nukkit.event.server.DataPacketSendEvent;
import cn.nukkit.inventory.transaction.action.InventoryAction;
import cn.nukkit.inventory.transaction.action.SlotChangeAction;
import cn.nukkit.math.BlockVector3;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.UpdateBlockPacket;
import org.battleplugins.nukkit.plugin.NukkitPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualInventoryListener implements Listener {

    private NukkitPlugin plugin;

    public VirtualInventoryListener(NukkitPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPacketSend(DataPacketSendEvent event) {
        DataPacket packet = event.getPacket();
        if (!(packet instanceof UpdateBlockPacket))
            return;

        UpdateBlockPacket updateBlock = (UpdateBlockPacket) packet;
        List<BlockVector3> positions = getVirtualInventoryPositions(event.getPlayer());
        if (positions == null)
            return;
            
        for (BlockVector3 pos : positions) {
            if (pos.x == updateBlock.x && pos.y == updateBlock.y && pos.z == updateBlock.z) { event.setCancelled();
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTransaction(InventoryTransactionEvent event) {
        Map<VirtualInventory, List<SlotChangeAction>> actions = new HashMap<>();
        Player source = event.getTransaction().getSource();
        for (InventoryAction action : event.getTransaction().getActions()) {
            if (!(action instanceof SlotChangeAction))
                continue;

            SlotChangeAction slotChange = (SlotChangeAction) action;

            if (!(slotChange.getInventory() instanceof VirtualInventory))
                continue;

            VirtualInventory inventory = (VirtualInventory) slotChange.getInventory();
            List<SlotChangeAction> slotChanges = actions.computeIfAbsent(inventory, fakeInventory -> new ArrayList<>());
            slotChanges.add(slotChange);
        }

        boolean cancel = false;
        for (Map.Entry<VirtualInventory, List<SlotChangeAction>> entry : actions.entrySet()) {
            for (SlotChangeAction action : entry.getValue()) {
                if (entry.getKey().onSlotChange(source, action))
                    cancel = true;
            }
        }

        if (cancel)
            event.setCancelled();
    }

    private List<BlockVector3> getVirtualInventoryPositions(Player player) {
        VirtualInventory inventory = VirtualInventory.getVirtualInventory(player);
        if (inventory == null) {
            return null;
        }
        return inventory.getPosition(player);
    }
}
