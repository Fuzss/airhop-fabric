package fuzs.airhop.client;

import fuzs.airhop.api.event.PlayerTickEvents;
import fuzs.airhop.client.handler.AirHopHandler;
import net.fabricmc.api.ClientModInitializer;

public class AirHopClient implements ClientModInitializer {
    public static void onConstructMod() {
        registerHandlers();
    }

    private static void registerHandlers() {
        final AirHopHandler airHopHandler = new AirHopHandler();
        PlayerTickEvents.END_TICK.register(airHopHandler::onPlayerTick$end);
    }

    @Override
    public void onInitializeClient() {
        onConstructMod();
    }
}
