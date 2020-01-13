package org.battleplugins.api.entity;

import org.battleplugins.api.util.NamespacedKey;

import java.util.Optional;

public class EntityTypes {

    public static final EntityType AREA_EFFECT_CLOUD = getOrDefault("area_effect_cloud");
    public static final EntityType ARMOR_STAND = getOrDefault("armor_stand");
    public static final EntityType ARROW = getOrDefault("arrow");
    public static final EntityType BAT = getOrDefault("bat");
    public static final EntityType BEE = getOrDefault("bee");
    public static final EntityType BLAZE = getOrDefault("blaze");
    public static final EntityType BOAT = getOrDefault("boat");
    public static final EntityType CAT = getOrDefault("cat");
    public static final EntityType CAVE_SPIDER = getOrDefault("cave_spider");
    public static final EntityType CHICKEN = getOrDefault("chicken");
    public static final EntityType COD = getOrDefault("cod");
    public static final EntityType COW = getOrDefault("cow");
    public static final EntityType CREEPER = getOrDefault("creeper");
    public static final EntityType DOLPHIN = getOrDefault("dolphin");
    public static final EntityType DONKEY = getOrDefault("donkey");
    public static final EntityType DRAGON_FIREBALL = getOrDefault("dragon_fireball");
    public static final EntityType ITEM = getOrDefault("item");
    public static final EntityType DROWNED = getOrDefault("drowned");
    public static final EntityType EGG = getOrDefault("egg");
    public static final EntityType ELDER_GUARDIAN = getOrDefault("elder_guardian");
    public static final EntityType ENDERMAN = getOrDefault("enderman");
    public static final EntityType ENDERMITE = getOrDefault("endermite");
    public static final EntityType END_CRYSTAL = getOrDefault("end_crystal");
    public static final EntityType ENDER_DRAGON = getOrDefault("ender_dragon");
    public static final EntityType ENDER_PEARL = getOrDefault("ender_pearl");
    public static final EntityType EYE_OF_ENDER = getOrDefault("eye_of_ender");
    public static final EntityType EVOKER = getOrDefault("evoker");
    public static final EntityType EVOKER_FANGS = getOrDefault("evoker_fangs");
    public static final EntityType EXPERIENCE_ORB = getOrDefault("experience_orb");
    public static final EntityType FALLING_BLOCK = getOrDefault("falling_block");
    public static final EntityType FIREBALL = getOrDefault("fireball");
    public static final EntityType FIREWORK_ROCKET = getOrDefault("firework_rocket");
    public static final EntityType FISHING_BOBBER = getOrDefault("fishing_bobber");
    public static final EntityType FOX = getOrDefault("fox");
    public static final EntityType GHAST = getOrDefault("ghast");
    public static final EntityType GIANT = getOrDefault("giant");
    public static final EntityType GUARDIAN = getOrDefault("guardian");
    public static final EntityType HORSE = getOrDefault("horse");
    public static final EntityType HUSK = getOrDefault("husk");
    public static final EntityType ILLUSIONER = getOrDefault("illusioner");
    public static final EntityType IRON_GOLEM = getOrDefault("iron_golem");
    public static final EntityType ITEM_FRAME = getOrDefault("item_frame");
    public static final EntityType LEASH_KNOT = getOrDefault("leash_knot");
    public static final EntityType LIGHTNING_BOLT = getOrDefault("lightning_bolt");
    public static final EntityType LLAMA = getOrDefault("llama");
    public static final EntityType LLAMA_SPIT = getOrDefault("llama_spit");
    public static final EntityType MAGMA_CUBE = getOrDefault("magma_cube");
    public static final EntityType MINECART = getOrDefault("minecart");
    public static final EntityType CHEST_MINECART = getOrDefault("chest_minecart");
    public static final EntityType COMMAND_BLOCK_MINECART = getOrDefault("command_block_minecart");
    public static final EntityType FURNACE_MINECART = getOrDefault("furnace_minecart");
    public static final EntityType HOPPER_MINECART = getOrDefault("hopper_minecart");
    public static final EntityType SPAWNER_MINECART = getOrDefault("spawner_minecart");
    public static final EntityType TNT_MINECART = getOrDefault("tnt_minecart");
    public static final EntityType MULE = getOrDefault("mule");
    public static final EntityType MOOSHROOM = getOrDefault("mooshroom");
    public static final EntityType OCELOT = getOrDefault("ocelot");
    public static final EntityType PAINTING = getOrDefault("painting");
    public static final EntityType PANDA = getOrDefault("panda");
    public static final EntityType PARROT = getOrDefault("parrot");
    public static final EntityType PHANTOM = getOrDefault("phantom");
    public static final EntityType PIG = getOrDefault("pig");
    public static final EntityType PILLAGER = getOrDefault("pillager");
    public static final EntityType PLAYER = getOrDefault("player");
    public static final EntityType POLAR_BEAR = getOrDefault("polar_bear");
    public static final EntityType TNT = getOrDefault("tnt");
    public static final EntityType PUFFERFISH = getOrDefault("pufferfish");
    public static final EntityType RABBIT = getOrDefault("rabbit");
    public static final EntityType RAVAGER = getOrDefault("ravager");
    public static final EntityType SALMON = getOrDefault("salmon");
    public static final EntityType SHEEP = getOrDefault("sheep");
    public static final EntityType SHULKER = getOrDefault("shulker");
    public static final EntityType SHULKER_BULLET = getOrDefault("shulker_bullet");
    public static final EntityType SILVERFISH = getOrDefault("silverfish");
    public static final EntityType SKELETON = getOrDefault("skeleton");
    public static final EntityType SKELETON_HORSE = getOrDefault("skeleton_horse");
    public static final EntityType SLIME = getOrDefault("slime");
    public static final EntityType SMALL_FIREBALL = getOrDefault("small_fireball");
    public static final EntityType SNOWBALL = getOrDefault("snowball");
    public static final EntityType SNOW_GOLEM = getOrDefault("snow_golem");
    public static final EntityType SPECTRAL_ARROW = getOrDefault("spectral_arrow");
    public static final EntityType SPIDER = getOrDefault("spider");
    public static final EntityType POTION = getOrDefault("potion");
    public static final EntityType SQUID = getOrDefault("squid");
    public static final EntityType STRAY = getOrDefault("stray");
    public static final EntityType EXPERIENCE_BOTTLE = getOrDefault("experience_bottle");
    public static final EntityType TRADER_LLAMA = getOrDefault("trader_llama");
    public static final EntityType TRIDENT = getOrDefault("trident");
    public static final EntityType TROPICAL_FISH = getOrDefault("tropical_fish");
    public static final EntityType TURTLE = getOrDefault("turtle");
    public static final EntityType VEX = getOrDefault("vex");
    public static final EntityType VILLAGER = getOrDefault("villager");
    public static final EntityType VINDICATOR = getOrDefault("vindicator");
    public static final EntityType WANDERING_TRADER = getOrDefault("wandering_trader");
    public static final EntityType WITCH = getOrDefault("witch");
    public static final EntityType WITHER = getOrDefault("wither");
    public static final EntityType WITHER_SKELETON = getOrDefault("wither_skeleton");
    public static final EntityType WITHER_SKULL = getOrDefault("wither_skull");
    public static final EntityType WOLF = getOrDefault("wolf");
    public static final EntityType ZOMBIE = getOrDefault("zombie");
    public static final EntityType ZOMBIE_HORSE = getOrDefault("zombie_horse");
    public static final EntityType ZOMBIE_PIGMAN = getOrDefault("zombie_pigman");
    public static final EntityType ZOMBIE_VILLAGER = getOrDefault("zombie_villager");

    /**
     * Gets an {@link EntityType} from the given {@link NamespacedKey}
     *
     * @param key the namespaced key to get the item from
     * @return an item type from the given namespaced key
     */
    public static Optional<EntityType> getEntityFromKey(NamespacedKey key) {
        return EntityRegistry.REGISTRY.fromKey(key);
    }

    private static EntityType getOrDefault(String name) {
        return getEntityFromKey(NamespacedKey.minecraft(name)).orElse(EntityTypes.PIG);
    }
}
