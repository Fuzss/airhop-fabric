package fuzs.airhop.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.capability.AirHopsCapabilityImpl;
import fuzs.airhop.enchantment.AirHopEnchantment;
import fuzs.puzzleslib.capability.CapabilityController;
import fuzs.puzzleslib.capability.data.PlayerRespawnStrategy;
import fuzs.puzzleslib.registry.RegistryManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModRegistry {
    private static final RegistryManager REGISTRY = RegistryManager.of(AirHop.MOD_ID);
    public static final Enchantment AIR_HOP_ENCHANTMENT = REGISTRY.registerEnchantment("air_hop", () -> new AirHopEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.FEET));
    public static final SoundEvent ENTITY_PLAYER_HOP_SOUND = REGISTRY.registerRawSoundEvent("entity.player.hop");

    private static final CapabilityController CAPABILITIES = CapabilityController.of(AirHop.MOD_ID);
    public static final ComponentKey<AirHopsCapability> AIR_HOPS_CAPABILITY = CAPABILITIES.registerPlayerCapability("air_hops", AirHopsCapability.class, player -> new AirHopsCapabilityImpl(), PlayerRespawnStrategy.NEVER);

    public static void touch() {

    }
}
