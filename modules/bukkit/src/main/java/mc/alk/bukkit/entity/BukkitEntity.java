package mc.alk.bukkit.entity;

import mc.alk.bukkit.BukkitLocation;
import mc.alk.bukkit.BukkitWorld;
import mc.alk.mc.entity.MCEntity;
import mc.alk.mc.util.MCWrapper;

import org.bukkit.entity.Entity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BukkitEntity extends MCWrapper<Entity> implements MCEntity {

    public BukkitEntity(Entity entity) {
        super(entity);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public UUID getUniqueId() {
        return handle.getUniqueId();
    }

    @Override
    public BukkitLocation getLocation() {
        return new BukkitLocation(handle.getLocation());
    }

    @Override
    public BukkitWorld getWorld() {
        return new BukkitWorld(handle.getWorld());
    }

    @Override
    public List<BukkitEntity> getNearbyEntities(double x, double y, double z) {
        return handle.getNearbyEntities(x, y, z).stream().map(BukkitEntity::new).collect(Collectors.toList());
    }

    @Override
    public String getCustomName() {
        return handle.getCustomName();
    }

    @Override
    public boolean isCustomNameVisible() {
        return handle.isCustomNameVisible();
    }

    @Override
    public void setCustomName(String customName) {
        handle.setCustomName(customName);
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        handle.setCustomNameVisible(visible);
    }
}
