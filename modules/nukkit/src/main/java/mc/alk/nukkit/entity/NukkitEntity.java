package mc.alk.nukkit.entity;

import cn.nukkit.entity.Entity;

import mc.alk.mc.MCLocation;
import mc.alk.mc.entity.MCEntity;
import mc.alk.mc.util.MCWrapper;
import mc.alk.nukkit.NukkitLocation;
import mc.alk.nukkit.NukkitWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NukkitEntity extends MCWrapper<Entity> implements MCEntity {

    public NukkitEntity(Entity entity) {
        super(entity);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public UUID getUniqueId() {
        return UUID.randomUUID(); // no support
    }

    @Override
    public NukkitLocation getLocation() {
        return new NukkitLocation(handle.getLocation());
    }

    @Override
    public NukkitWorld getWorld() {
        return new NukkitWorld(handle.getLevel());
    }

    @Override
    public boolean teleport(MCLocation location) {
        return handle.teleport(((NukkitLocation) location).getHandle());
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
        return Stream.of(handle.getLevel().getNearbyEntities(handle.getBoundingBox().clone()
                .grow(x, y, z))).map(NukkitEntity::new).collect(Collectors.toList());
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
}
