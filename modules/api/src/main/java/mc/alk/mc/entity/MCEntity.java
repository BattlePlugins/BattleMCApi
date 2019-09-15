package mc.alk.mc.entity;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

import java.util.List;
import java.util.UUID;

public interface MCEntity {

    String getName();
    UUID getUniqueId();

    MCLocation getLocation();
    MCWorld getWorld();

    List<? extends MCEntity> getNearbyEntities(double x, double y, double z);

    String getCustomName();
    boolean isCustomNameVisible();

    void setCustomName(String customName);
    void setCustomNameVisible(boolean visible);
}
