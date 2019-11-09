package mc.alk.nukkit.entity;

import cn.nukkit.entity.EntityLiving;

import mc.alk.mc.entity.MCEntity;
import mc.alk.mc.entity.MCLivingEntity;

public class NukkitLivingEntity extends NukkitEntity implements MCLivingEntity {

    public NukkitLivingEntity(EntityLiving entity) {
        super(entity);
    }

    @Override
    public void damage(double amount) {
        getHandle().attack((float) amount);
    }

    @Override
    public void damage(double amount, MCEntity source) {
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
    public EntityLiving getHandle() {
        return (EntityLiving) super.getHandle();
    }
}
