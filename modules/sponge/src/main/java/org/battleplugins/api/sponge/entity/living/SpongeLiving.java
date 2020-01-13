package org.battleplugins.api.sponge.entity.living;

import org.battleplugins.api.entity.Entity;
import org.battleplugins.api.entity.living.Living;
import org.battleplugins.api.sponge.entity.SpongeEntity;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;

public class SpongeLiving<T extends org.spongepowered.api.entity.living.Living> extends SpongeEntity<T> implements Living {

    public SpongeLiving(T entity) {
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
