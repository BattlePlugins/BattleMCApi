package org.battleplugins.api.inventory.carrier;

import org.battleplugins.api.inventory.Inventory;
import org.battleplugins.api.inventory.CarriedInventory;

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
