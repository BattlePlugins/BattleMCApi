package mc.alk.mc.entity;

import mc.alk.mc.inventory.MCPlayerInventory;

public interface MCHumanEntity extends MCLivingEntity {

    MCPlayerInventory getInventory();
}
