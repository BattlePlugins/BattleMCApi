package mc.alk.nukkit.entity;

import cn.nukkit.entity.EntityHuman;

import mc.alk.mc.entity.MCHumanEntity;
import mc.alk.mc.inventory.MCPlayerInventory;
import mc.alk.nukkit.inventory.NukkitPlayerInventory;

import java.util.UUID;

public class NukkitHumanEntity extends NukkitLivingEntity implements MCHumanEntity {

    private EntityHuman humanEntity;

    public NukkitHumanEntity(EntityHuman humanEntity) {
        super(humanEntity);

        this.humanEntity = humanEntity;
    }

    @Override
    public NukkitPlayerInventory getInventory() {
        return new NukkitPlayerInventory(humanEntity.getInventory());
    }

    @Override
    public UUID getUniqueId() {
        return humanEntity.getUniqueId();
    }
}
