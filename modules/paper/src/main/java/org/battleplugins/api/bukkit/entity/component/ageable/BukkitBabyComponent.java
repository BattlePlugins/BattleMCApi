package org.battleplugins.api.bukkit.entity.component.ageable;

import org.battleplugins.api.bukkit.entity.BukkitEntity;
import org.battleplugins.api.entity.Entity;
import org.battleplugins.api.entity.component.ageable.BabyComponent;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Zombie;

import java.util.Optional;

public class BukkitBabyComponent implements BabyComponent {

    @Override
    public void applyComponent(Entity entity, Boolean baby) {
        org.bukkit.entity.Entity bukkitEntity = ((BukkitEntity) entity).getHandle();
        if (entity instanceof Ageable) {
            Ageable ageable = (Ageable) bukkitEntity;
            if (baby)
                ageable.setBaby();
            else
                ageable.setAdult();
        }

        if (entity instanceof Zombie) {
            Zombie zombie = (Zombie) bukkitEntity;
            zombie.setBaby(baby);
        }
    }

    @Override
    public Optional<Boolean> getValue(Entity entity) {
        org.bukkit.entity.Entity bukkitEntity = ((BukkitEntity) entity).getHandle();
        if (entity instanceof Ageable)
            return Optional.of(!((Ageable) bukkitEntity).isAdult());

        if (entity instanceof Zombie)
            return Optional.of(((Zombie) bukkitEntity).isBaby());

        return Optional.empty();
    }

    @Override
    public boolean isAppliable(Entity entity) {
        org.bukkit.entity.Entity bukkitEntity = ((BukkitEntity) entity).getHandle();
        return bukkitEntity instanceof Ageable || bukkitEntity instanceof Zombie;
    }
}
