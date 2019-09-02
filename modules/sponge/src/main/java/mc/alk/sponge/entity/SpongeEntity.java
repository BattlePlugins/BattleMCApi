package mc.alk.sponge.entity;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;
import mc.alk.mc.entity.MCEntity;
import mc.alk.sponge.SpongeLocation;
import mc.alk.sponge.SpongeWorld;
import mc.alk.sponge.util.SpongeUtil;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpongeEntity implements MCEntity {

    private Entity entity;

    public SpongeEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String getName() {
        return entity.getType().getName();
    }

    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    @Override
    public MCLocation getLocation() {
        return new SpongeLocation(entity.getLocation());
    }

    @Override
    public MCWorld getWorld() {
        return new SpongeWorld(entity.getWorld());
    }

    @Override
    public List<MCEntity> getNearbyEntities(double x, double y, double z) {
        List<MCEntity> entities = new ArrayList<>();
        SpongeUtil.getNearbyEntities(entity, x, y, z).forEach(spongeEntity -> entities.add(new SpongeEntity(spongeEntity)));
        return entities;
    }

    @Override
    public String getCustomName() {
        if (!entity.get(Keys.DISPLAY_NAME).isPresent()) {
            return entity.getType().getName();
        }
        return entity.get(Keys.DISPLAY_NAME).get().toPlain();
    }

    @Override
    public boolean isCustomNameVisible() {
        if (!entity.get(Keys.CUSTOM_NAME_VISIBLE).isPresent()) {
            return false;
        }
        return entity.get(Keys.CUSTOM_NAME_VISIBLE).get();
    }

    @Override
    public void setCustomName(String customName) {
        entity.offer(Keys.DISPLAY_NAME, Text.of(customName));
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        entity.offer(Keys.CUSTOM_NAME_VISIBLE, visible);
    }
}
