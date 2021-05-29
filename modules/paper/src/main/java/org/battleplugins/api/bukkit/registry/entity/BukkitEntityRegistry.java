package org.battleplugins.api.bukkit.registry.entity;

import org.battleplugins.api.bukkit.entity.BukkitEntityType;
import org.battleplugins.api.registry.entity.EntityRegistry;
import org.battleplugins.api.util.Identifier;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BukkitEntityRegistry extends EntityRegistry {

    private static final Map<String, String> LEGACY_NAMES = new HashMap<String, String>(){
        {
            put("EXPERIENCE_ORB", "XP_ORB");
            put("ENDER_SIGNAL", "EYE_OF_ENDER_SIGNAL");
            put("THROWN_EXP_BOTTLE", "XP_BOTTLE");
            put("FIREWORK", "FIREWORKS_ROCKET");
            put("EVOKER_FANGS", "EVOCATION_FANGS");
            put("EVOKER", "EVOCATION_ILLAGER");
            put("VINDICATOR", "VINDICATION_ILLAGER");
            put ("ILLUSIONER", "ILLUSION_ILLAGER");
            put ("MINECART_COMMAND", "COMMANDBLOCK_MINECART");
            put("SNOW_GOLEM", "SNOWMAN");
            put("IRON_GOLEM", "VILLAGER_GOLEM");
            put("END_CRYSTAL", "ENDER_CRYSTAL");
            put("MOOSHROOM", "MUSHROOM_COW");
            put("ZOMBIFIED_PIGLIN", "PIG_ZOMBIE");
        }
    };

    @Override
    public Optional<org.battleplugins.api.entity.EntityType> fromIdentifier(Identifier identifier) {
        EntityType type = EntityType.fromName(identifier.getKey());
        if (type == null) {
            String legacyName = LEGACY_NAMES.get(identifier.getKey().toUpperCase());
            if (legacyName == null) {
                throw new IllegalArgumentException("Could not find entity type with identifier " + identifier.toString() + "! Please consider reporting this!");
            } else {
                type = EntityType.fromName(legacyName);
            }
        }
        return Optional.ofNullable(type).map(BukkitEntityType::new);
    }
}
