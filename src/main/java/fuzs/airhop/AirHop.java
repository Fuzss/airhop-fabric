package fuzs.airhop;

import fuzs.airhop.api.event.LivingFallEvents;
import fuzs.airhop.api.event.PlayerTickEvents;
import fuzs.airhop.config.ServerConfig;
import fuzs.airhop.handler.PlayerFallHandler;
import fuzs.airhop.handler.PlayerSyncHandler;
import fuzs.airhop.network.client.message.C2SAirHopMessage;
import fuzs.airhop.network.message.S2CSyncAirHopsMessage;
import fuzs.airhop.registry.ModRegistry;
import fuzs.puzzleslib.config.AbstractConfig;
import fuzs.puzzleslib.config.ConfigHolder;
import fuzs.puzzleslib.config.ConfigHolderImpl;
import fuzs.puzzleslib.network.MessageDirection;
import fuzs.puzzleslib.network.NetworkHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirHop implements ModInitializer {
    public static final String MOD_ID = "airhop";
    public static final String MOD_NAME = "Air Hop";
    public static final Logger LOGGER = LogManager.getLogger(AirHop.MOD_NAME);

    public static final NetworkHandler NETWORK = NetworkHandler.of(MOD_ID);
    @SuppressWarnings("Convert2MethodRef")
    public static final ConfigHolder<AbstractConfig, ServerConfig> CONFIG = ConfigHolder.server(() -> new ServerConfig());

    public static void onConstructMod() {
        ((ConfigHolderImpl<?, ?>) CONFIG).addConfigs(MOD_ID);
        ModRegistry.touch();
        registerMessages();
        registerHandlers();
    }

    private static void registerHandlers() {
        final PlayerFallHandler playerFallHandler = new PlayerFallHandler();
        PlayerTickEvents.START_TICK.register(playerFallHandler::onPlayerTick$start);
        LivingFallEvents.MODIFY_FALL_DISTANCE.register(playerFallHandler::onLivingFall);
        final PlayerSyncHandler playerSyncHandler = new PlayerSyncHandler();
        ServerEntityEvents.ENTITY_LOAD.register(playerSyncHandler::onEntityJoinWorld);
    }

    private static void registerMessages() {
        NETWORK.register(S2CSyncAirHopsMessage.class, S2CSyncAirHopsMessage::new, MessageDirection.TO_CLIENT);
        NETWORK.register(C2SAirHopMessage.class, C2SAirHopMessage::new, MessageDirection.TO_SERVER);
    }

    @Override
    public void onInitialize() {
        onConstructMod();
    }
}
