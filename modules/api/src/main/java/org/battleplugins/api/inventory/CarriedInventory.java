package org.battleplugins.api.inventory;

import org.battleplugins.api.inventory.carrier.Carrier;

/**
 * Represents an inventory that can be carried.
 *
 * @param <T> the carrier
 */
public interface CarriedInventory<T extends Carrier> extends Inventory {

    /**
     * The {@link Carrier} of the inventory
     *
     * @return the carrier of the inventory
     */
    T getCarrier();
}
