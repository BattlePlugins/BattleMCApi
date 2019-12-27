package org.battleplugins.api.nukkit.entity.living;

import cn.nukkit.entity.EntityLiving;

import org.battleplugins.api.entity.Entity;
import org.battleplugins.api.entity.living.LivingEntity;
import org.battleplugins.api.nukkit.entity.NukkitEntity;

public class NukkitLivingEntity<T extends EntityLiving> extends NukkitEntity<T> implements LivingEntity {

    public NukkitLivingEntity(T entity) {
        super(entity);
    }

    @Override
    public void damage(double amount) {
        getHandle().attack((float) amount);
    }

    @Override
    public void damage(double amount, Entity source) {
        getHandle().attack((float) amount); // no support for source yet
    }

    @Override
    public double getHealth() {
        return getHandle().getHealth();
    }

    @Override
    public void setHealth(double health) {
        getHandle().setHealth((float) health);
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
