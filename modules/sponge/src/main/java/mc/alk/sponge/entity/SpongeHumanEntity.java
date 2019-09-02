package mc.alk.sponge.entity;

import mc.alk.mc.inventory.MCHumanEntity;
import mc.alk.mc.inventory.MCPlayerInventory;
import mc.alk.sponge.inventory.SpongePlayerInventory;

import org.spongepowered.api.entity.living.Humanoid;

public class SpongeHumanEntity extends SpongeLivingEntity implements MCHumanEntity {

    private Humanoid humanEntity;

    public SpongeHumanEntity(Humanoid humanEntity) {
        super(humanEntity);

        this.humanEntity = humanEntity;
    }

    @Override
    public MCPlayerInventory getInventory() {
        return new SpongePlayerInventory(humanEntity.getInventory());
    }

}
