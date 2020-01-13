package org.battleplugins.api.nukkit.entity.living;

import cn.nukkit.entity.EntityLiving;

import org.battleplugins.api.entity.Entity;
import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.entity.living.Living;
import org.battleplugins.api.nukkit.entity.NukkitEntity;

public class NukkitLiving<T extends EntityLiving> extends NukkitEntity<T> implements Living {

    public NukkitLiving(T entity) {
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
