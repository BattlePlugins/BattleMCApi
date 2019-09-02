package mc.alk.sponge.entity;

import mc.alk.mc.entity.MCLivingEntity;

import org.spongepowered.api.entity.living.Living;

public class SpongeLivingEntity extends SpongeEntity implements MCLivingEntity {

    public SpongeLivingEntity(Living entity) {
        super(entity);
    }
}
