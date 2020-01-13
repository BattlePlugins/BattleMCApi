package org.battleplugins.api.bukkit.entity;

import io.papermc.lib.PaperLib;

import org.battleplugins.api.bukkit.util.BukkitUtil;
import org.battleplugins.api.bukkit.world.BukkitWorld;
import org.battleplugins.api.entity.EntityType;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.util.NamespacedKey;
import org.battleplugins.api.world.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BukkitEntity<T extends Entity> extends MCWrapper<T> implements org.battleplugins.api.entity.Entity {

    public BukkitEntity(T entity) {
        super(entity);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public NamespacedKey getKey() {
        return NamespacedKey.minecraft(handle.getType().getName());
    }

    @Override
    public EntityType getType() {
        return new BukkitEntityType(handle.getType());
    }

    @Override
    public UUID getUniqueId() {
        return handle.getUniqueId();
    }

    @Override
    public Location getLocation() {
        return BukkitUtil.fromBukkitLocation(handle.getLocation());
    }

    @Override
    public BukkitWorld getWorld() {
        return new BukkitWorld(handle.getWorld());
    }

    @Override
    public boolean teleport(Location location) {
        return PaperLib.teleportAsync(handle, BukkitUtil.toBukkitLocation(location)).getNow(true);
    }

    @Override
    public boolean isDead() {
        return handle.isDead();
    }

    @Override
    public boolean isValid() {
        return handle.isValid();
    }

    @Override
    public List<BukkitEntity> getNearbyEntities(double x, double y, double z) {
        List<BukkitEntity> entities = new ArrayList<>();
        for (org.bukkit.entity.Entity entity : handle.getNearbyEntities(x, y, z)) {
            entities.add(new BukkitEntity<>(entity));
        }
        return entities;
    }

    @Override
    public Optional<String> getCustomName() {
        return Optional.ofNullable(handle.getCustomName());
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

    @Override
    public T getHandle() {
        return handle;
    }
}
