package mc.alk.bukkit.entity;

import mc.alk.bukkit.BukkitLocation;
import mc.alk.bukkit.BukkitWorld;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;
import mc.alk.mc.entity.MCEntity;

import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BukkitEntity implements MCEntity {

    private Entity entity;

    public BukkitEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    @Override
    public MCLocation getLocation() {
        return new BukkitLocation(entity.getLocation());
    }

    @Override
    public MCWorld getWorld() {
        return new BukkitWorld(entity.getWorld());
    }

    @Override
    public List<MCEntity> getNearbyEntities(double x, double y, double z) {
        List<MCEntity> entities = new ArrayList<>();
        entity.getNearbyEntities(x, y, z).forEach(entity -> entities.add(new BukkitEntity(entity)));
        return entities;
    }

    @Override
    public String getCustomName() {
        return entity.getCustomName();
    }

    @Override
    public boolean isCustomNameVisible() {
        return entity.isCustomNameVisible();
    }

    @Override
    public void setCustomName(String customName) {
        entity.setCustomName(customName);
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        entity.setCustomNameVisible(visible);
    }
}
