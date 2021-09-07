package co.eltrut.differentiate.client;

import co.eltrut.differentiate.core.registrator.Registrator;
import net.fabricmc.api.ClientModInitializer;

public class DifferentiateClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Registrator.registerClient();
    }
}