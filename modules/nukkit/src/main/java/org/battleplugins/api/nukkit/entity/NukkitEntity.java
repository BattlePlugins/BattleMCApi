package org.battleplugins.api.nukkit.entity;

import cn.nukkit.entity.Entity;

import org.battleplugins.api.nukkit.util.NukkitUtil;
import org.battleplugins.api.nukkit.world.NukkitWorld;
import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.world.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NukkitEntity<T extends Entity> extends MCWrapper<T> implements org.battleplugins.api.entity.Entity {

    public NukkitEntity(T entity) {
        super(entity);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public NukkitEntityType getType() {
        return new NukkitEntityType(handle.getNetworkId());
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
    public List<org.battleplugins.api.entity.Entity> getNearbyEntities(double x, double y, double z) {
        List<org.battleplugins.api.entity.Entity> entityList = new ArrayList<>();
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
