package org.battleplugins;

import mc.euro.version.Version;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the default platform types this API
 * offers implementations for.
 */
public class PlatformTypes {

    /**
     * A set of all the {@link PlatformType}s registered within
     * this API. Any custom implementation should create their new
     * platform type before plugins are loaded.
     *
     * Upon building a platform type {@link PlatformType#builder()},
     * they are automatically registered into this set.
     */
    static Set<PlatformType> platformTypes = new HashSet<>();

    /**
     * The Bukkit Platform: https://bukkit.org
     */
    public static PlatformType BUKKIT = new PlatformType("Bukkit", new Version<>("1.8.8"));

    /**
     * The Nukkit Platform: https://nukkitx.com
     */
    public static PlatformType NUKKIT = new PlatformType("Nukkit", new Version<>("1.0.0"));

    /**
     * The Sponge Platform: https://spongepowered.org
     */
    public static PlatformType SPONGE = new PlatformType("Sponge", new Version<>("7.1.0"));

    /**
     * An array of all the {@link PlatformType}s
     *
     * @return an array of all the platform types
     */
    public static PlatformType[] values() {
        return platformTypes.toArray(new PlatformType[0]);
    }
}
