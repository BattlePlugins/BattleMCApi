package org.battleplugins.nukkit.entity;

import cn.nukkit.entity.Entity;

import org.battleplugins.nukkit.util.NukkitUtil;
import org.battleplugins.nukkit.world.NukkitWorld;
import org.battleplugins.util.MCWrapper;
import org.battleplugins.util.NamespacedKey;
import org.battleplugins.world.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NukkitEntity<T extends Entity> extends MCWrapper<T> implements org.battleplugins.entity.Entity {

    public NukkitEntity(T entity) {
        super(entity);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public NamespacedKey getKey() {
        return NamespacedKey.minecraft(handle.getSaveId().replace("Entity", ""));
    }

    @Override
    public UUID getUniqueId() {
        return UUID.randomUUID(); // no support
    }

    @Override
    public Location getLocation() {
        return NukkitUtil.fromNukkitLocation(handle.getLocation());
    }

    @Override
    public NukkitWorld getWorld() {
        return new NukkitWorld(handle.getLevel());
    }

    @Override
    public boolean teleport(Location location) {
        return handle.teleport(NukkitUtil.toNukkitLocation(location));
    }

    @Override
    public boolean isDead() {
        return !handle.isValid();
    }

    @Override
    public boolean isValid() {
        return handle.isValid();
    }

    @Override
    public List<NukkitEntity> getNearbyEntities(double x, double y, double z) {
        List<NukkitEntity> entityList = new ArrayList<>();
        for (Entity entity : handle.getLevel().getNearbyEntities(handle.getBoundingBox().clone().grow(x, y, z))) {
            entityList.add(new NukkitEntity<>(entity));
        }
        return entityList;
    }

    @Override
    public Optional<String> getCustomName() {
        return Optional.ofNullable(handle.getNameTag());
    }

    @Override
    public boolean isCustomNameVisible() {
        return handle.hasCustomName();
    }

    @Override
    public void setCustomName(String customName) {
        handle.setNameTag(customName);
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        handle.setNameTagVisible(visible);
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
