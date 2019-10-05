package mc.alk.bukkit.entity;

import mc.alk.mc.entity.MCEntity;
import mc.alk.mc.entity.MCLivingEntity;

import org.bukkit.entity.LivingEntity;

public class BukkitLivingEntity extends BukkitEntity implements MCLivingEntity {

    public BukkitLivingEntity(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void damage(double amount) {
        getHandle().damage(amount);
    }

    @Override
    public void damage(double amount, MCEntity source) {
        getHandle().damage(amount, ((BukkitEntity) source).getHandle());
    }

    @Override
    public double getHealth() {
        return getHandle().getHealth();
    }

    @Override
    public void setHealth(double health) {
        getHandle().setHealth(health);
    }

    @Override
    public LivingEntity getHandle() {
        return (LivingEntity) super.handle;
    }
}
