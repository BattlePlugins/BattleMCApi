package mc.alk.nukkit.entity;

import cn.nukkit.entity.Entity;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;
import mc.alk.mc.entity.MCEntity;
import mc.alk.nukkit.NukkitLocation;
import mc.alk.nukkit.NukkitWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NukkitEntity implements MCEntity {

    private Entity entity;

    public NukkitEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public UUID getUniqueId() {
        return UUID.randomUUID(); // no support
    }

    @Override
    public MCLocation getLocation() {
        return new NukkitLocation(entity.getLocation());
    }

    @Override
    public MCWorld getWorld() {
        return new NukkitWorld(entity.getLevel());
    }

    @Override
    public List<MCEntity> getNearbyEntities(double x, double y, double z) {
        List<MCEntity> entities = new ArrayList<>();
        for (Entity entity : entity.getLevel().getNearbyEntities(entity.getBoundingBox().clone().grow(x, y, z))) {
            entities.add(new NukkitEntity(entity));
        }
        return entities;
    }

    @Override
    public String getCustomName() {
        return entity.getNameTag();
    }

    @Override
    public boolean isCustomNameVisible() {
        return entity.hasCustomName();
    }

    @Override
    public void setCustomName(String customName) {
        entity.setNameTag(customName);
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        entity.setNameTagVisible(visible);
    }
}
