package fuzs.airhop.handler;

import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.registry.ModRegistry;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public class PlayerFallHandler {
    public void onPlayerTick$start(Player player) {
        // start is important, otherwise this runs before fall distance is processed
        if (player.isOnGround() && player.getDeltaMovement().y() <= 0.0) {
            ModRegistry.AIR_HOPS_CAPABILITY.maybeGet(player).ifPresent(AirHopsCapability::resetAirHops);
        }
    }

    public float onLivingFall(LivingEntity entity, float distance, float damageMultiplier) {
        if (entity instanceof Player player) {
            // survival mode
            return this.onGroundHit(player, distance);
        }
        return distance;
    }

    private float onGroundHit(Player player, float fallDistance) {
        Optional<AirHopsCapability> optional = ModRegistry.AIR_HOPS_CAPABILITY.maybeGet(player);
        if (optional.isPresent()) {
            int airHops = optional.orElseThrow(IllegalStateException::new).getAirHops();
            if (!AirHop.CONFIG.server().fallDamage && airHops > 0) {
                return Math.max(0.0F, fallDistance - airHops * getJumpHeight(player));
            }
        }
        return fallDistance;
    }

    public static float getJumpHeight(Player player) {
        float jumpHeight = 1.25F;
        if (player.hasEffect(MobEffects.JUMP)) {
            jumpHeight += 0.6875F * (player.getEffect(MobEffects.JUMP).getAmplifier() + 1.0F);
        }
        return jumpHeight;
    }

}
