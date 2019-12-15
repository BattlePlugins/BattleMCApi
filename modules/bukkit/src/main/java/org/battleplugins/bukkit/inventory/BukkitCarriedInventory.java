package org.battleplugins.bukkit.inventory;

import org.battleplugins.inventory.CarriedInventory;
import org.battleplugins.inventory.carrier.Carrier;
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
