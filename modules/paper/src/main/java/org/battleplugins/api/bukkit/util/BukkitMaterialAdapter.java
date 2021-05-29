package org.battleplugins.api.bukkit.util;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;

import java.util.Optional;

public class BukkitMaterialAdapter {

    public static Optional<Material> getMaterial(String mat) {
        Material material = null;
        try {
            material = Registry.MATERIAL.get(NamespacedKey.fromString(mat));
            if (material != null) {
                material = Material.matchMaterial(mat.toUpperCase());
            }
        } catch (NullPointerException ignored) {
        }

        return Optional.ofNullable(material);
    }
}
