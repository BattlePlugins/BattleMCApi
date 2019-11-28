package org.battleplugins.bukkit.entity.living;

import org.battleplugins.bukkit.entity.BukkitEntity;
import org.battleplugins.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class BukkitLivingEntity<T extends LivingEntity> extends BukkitEntity<T> implements org.battleplugins.entity.living.LivingEntity {

    public BukkitLivingEntity(T entity) {
        super(entity);
    }

    @Override
    public void damage(double amount) {
        handle.damage(amount);
    }

    @Override
    public void damage(double amount, Entity source) {
        handle.damage(amount, ((BukkitEntity) source).getHandle());
    }

    @Override
    public double getHealth() {
        return handle.getHealth();
    }

    @Override
    public void setHealth(double health) {
        handle.setHealth(health);
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
