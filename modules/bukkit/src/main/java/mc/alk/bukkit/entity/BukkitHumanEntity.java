package mc.alk.bukkit.entity;

import mc.alk.bukkit.inventory.BukkitPlayerInventory;
import mc.alk.mc.inventory.MCHumanEntity;
import mc.alk.mc.inventory.MCPlayerInventory;

import org.bukkit.entity.HumanEntity;

public class BukkitHumanEntity extends BukkitLivingEntity implements MCHumanEntity {

    private HumanEntity humanEntity;

    public BukkitHumanEntity(HumanEntity humanEntity) {
        super(humanEntity);

        this.humanEntity = humanEntity;
    }

    @Override
    public MCPlayerInventory getInventory() {
        return new BukkitPlayerInventory(humanEntity.getInventory());
    }
}
