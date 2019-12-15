package org.battleplugins.nukkit.inventory;

import cn.nukkit.inventory.Inventory;

import org.battleplugins.inventory.CarriedInventory;
import org.battleplugins.inventory.carrier.Carrier;

public class NukkitCarriedInventory<T extends Inventory, C extends Carrier> extends NukkitInventory<T> implements CarriedInventory<C> {

    private C carrier;

    public NukkitCarriedInventory(T inventory, C carrier) {
        super(inventory);

        this.carrier = carrier;
    }

    @Override
    public C getCarrier() {
        return carrier;
    }
}