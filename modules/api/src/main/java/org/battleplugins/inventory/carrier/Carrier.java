package org.battleplugins.inventory.carrier;

import org.battleplugins.inventory.CarriedInventory;
import org.battleplugins.inventory.Inventory;

/**
 * Represents an object capable of holding an {@link Inventory}.
 */
public interface Carrier {

    /**
     * The inventory of the carrier
     *
     * @return the inventory of the carrier
     */
    CarriedInventory<? extends Carrier> getInventory();
}
