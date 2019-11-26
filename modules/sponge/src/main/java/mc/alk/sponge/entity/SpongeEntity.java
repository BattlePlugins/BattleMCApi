package mc.alk.sponge.entity;

import mc.alk.mc.MCLocation;
import mc.alk.mc.entity.MCEntity;
import mc.alk.mc.util.MCWrapper;
import mc.alk.sponge.SpongeLocation;
import mc.alk.sponge.SpongeWorld;
import mc.alk.sponge.util.SpongeUtil;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class SpongeEntity extends MCWrapper<Entity> implements MCEntity {

    public SpongeEntity(Entity entity) {
        super(entity);
    }

    @Override
    public String getName() {
        return handle.getType().getName();
    }

    @Override
    public UUID getUniqueId() {
        return handle.getUniqueId();
    }

    @Override
    public SpongeLocation getLocation() {
        return new SpongeLocation(handle.getLocation());
    }

    @Override
    public SpongeWorld getWorld() {
        return new SpongeWorld(handle.getWorld());
    }

    @Override
    public boolean teleport(MCLocation location) {
        return handle.setLocation(((SpongeLocation) location).getHandle());
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
        return SpongeUtil.getNearbyEntities(handle, x, y, z).stream()
                .map(SpongeEntity::new).collect(Collectors.toList());
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
}
