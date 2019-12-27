package org.battleplugins.api.bukkit.inventory;

import org.battleplugins.api.inventory.CarriedInventory;
import org.battleplugins.api.inventory.carrier.Carrier;
import org.bukkit.inventory.Inventory;

public class BukkitCarriedInventory<T extends Inventory, C extends Carrier> extends BukkitInventory<T> implements CarriedInventory<C> {

    private C carrier;

    public BukkitCarriedInventory(T inventory, C carrier) {
        super(inventory);

        this.carrier = carrier;
    }

    @Override
    public C getCarrier() {
        return carrier;
    }
}
