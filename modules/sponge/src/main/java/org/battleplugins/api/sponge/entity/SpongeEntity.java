package org.battleplugins.api.sponge.entity;

import org.battleplugins.api.util.MCWrapper;
import org.battleplugins.api.util.NamespacedKey;
import org.battleplugins.api.world.Location;
import org.battleplugins.api.sponge.world.SpongeWorld;
import org.battleplugins.api.sponge.util.SpongeUtil;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SpongeEntity<T extends Entity> extends MCWrapper<T> implements org.battleplugins.api.entity.Entity {

    public SpongeEntity(T entity) {
        super(entity);
    }

    @Override
    public String getName() {
        return handle.getType().getName();
    }

    @Override
    public NamespacedKey getKey() {
        return NamespacedKey.of(handle.getType().getId().split(":")[0], handle.getType().getId().split(":")[1]);
    }

    @Override
    public UUID getUniqueId() {
        return handle.getUniqueId();
    }

    @Override
    public Location getLocation() {
        return SpongeUtil.fromSpongeLocation(handle.getLocation());
    }

    @Override
    public SpongeWorld getWorld() {
        return new SpongeWorld(handle.getWorld());
    }

    @Override
    public boolean teleport(Location location) {
        return handle.setLocation(SpongeUtil.toSpongeLocation(location));
    }

    @Override
    public boolean isDead() {
        return handle.isRemoved();
    }

    @Override
    public boolean isValid() {
        return handle.isLoaded() && !handle.isRemoved();
    }

    @Override
    public List<SpongeEntity> getNearbyEntities(double x, double y, double z) {
        List<SpongeEntity> entities = new ArrayList<>();
        for (Entity entity : SpongeUtil.getNearbyEntities(handle, x, y, z)) {
            entities.add(new SpongeEntity<>(entity));
        }
        return entities;
    }

    @Override
    public Optional<String> getCustomName() {
        return handle.get(Keys.DISPLAY_NAME).map(Text::toPlain);
    }

    @Override
    public boolean isCustomNameVisible() {
        return handle.get(Keys.CUSTOM_NAME_VISIBLE).orElse(false);
    }

    @Override
    public void setCustomName(String customName) {
        handle.offer(Keys.DISPLAY_NAME, Text.of(customName));
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        handle.offer(Keys.CUSTOM_NAME_VISIBLE, visible);
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
