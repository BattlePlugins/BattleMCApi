package org.battleplugins.api.bukkit.util;

import org.battleplugins.api.bukkit.compat.BukkitCompatMaterial;
import org.bukkit.Material;

import java.util.Optional;

public class BukkitMaterialAdapter {

    public static Optional<Material> getMaterial(String mat) {
        Optional<Material> material = Optional.empty();
        try {
            material = BukkitCompatMaterial.fromString(mat).map(BukkitCompatMaterial::parseMaterial);
            if (!material.isPresent()) {
                material = Optional.ofNullable(Material.matchMaterial(mat.toUpperCase()));
            }
            if (!material.isPresent()) {
                material = Optional.ofNullable(Material.matchMaterial("LEGACY_" + mat.toUpperCase()));
            }
        } catch (NullPointerException ignored) {
        }

        return material;
    }
}
