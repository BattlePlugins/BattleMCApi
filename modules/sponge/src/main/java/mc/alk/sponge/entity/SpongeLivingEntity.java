package mc.alk.sponge.entity;

import mc.alk.mc.entity.MCEntity;
import mc.alk.mc.entity.MCLivingEntity;

import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;

public class SpongeLivingEntity extends SpongeEntity implements MCLivingEntity {

    public SpongeLivingEntity(Living entity) {
        super(entity);
    }

    @Override
    public void damage(double amount) {
        handle.damage(amount, DamageSource.builder().type(DamageTypes.GENERIC).build());
    }

    @Override
    public void damage(double amount, MCEntity source) {
        handle.damage(amount, EntityDamageSource.builder().entity(((SpongeEntity) source).getHandle()).build());
    }

    @Override
    public double getHealth() {
        return getHandle().getHealthData().health().get();
    }

    @Override
    public void setHealth(double health) {
        getHandle().getHealthData().health().set(health);
    }

    @Override
    public Living getHandle() {
        return (Living) super.getHandle();
    }
}
