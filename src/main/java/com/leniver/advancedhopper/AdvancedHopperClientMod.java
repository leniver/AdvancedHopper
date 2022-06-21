package com.leniver.advancedhopper;

import com.leniver.advancedhopper.screen.AdvancedHopperScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class AdvancedHopperClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(AdvancedHopperMod.ADVANCED_HOPPER_SCREEN_HANDLER_TYPE, AdvancedHopperScreen::new);
    }
}
