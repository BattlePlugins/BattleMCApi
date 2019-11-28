package org.battleplugins.sponge.entity.living;

import org.battleplugins.entity.Entity;
import org.battleplugins.entity.living.LivingEntity;
import org.battleplugins.sponge.entity.SpongeEntity;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;

public class SpongeLivingEntity<T extends Living> extends SpongeEntity<T> implements LivingEntity {

    public SpongeLivingEntity(T entity) {
        super(entity);
    }

    @Override
    public void damage(double amount) {
        handle.damage(amount, DamageSource.builder().type(DamageTypes.GENERIC).build());
    }

    @Override
    public void damage(double amount, Entity source) {
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
    public T getHandle() {
        return handle;
    }
}
