package com.leniver.advancedhopper;

import com.leniver.advancedhopper.block.AdvancedHopperBlock;
import com.leniver.advancedhopper.block.AdvancedHopperBlockEntity;
import com.leniver.advancedhopper.block.AdvancedHopperItem;
import com.leniver.advancedhopper.polymer.PolymerAdvancedHopperBlock;
import com.leniver.advancedhopper.polymer.PolymerAdvancedHopperBlockEntity;
import com.leniver.advancedhopper.polymer.PolymerAdvancedHopperItem;
import com.leniver.advancedhopper.screen.AdvancedHopperScreen;
import com.leniver.advancedhopper.screen.AdvancedHopperScreenHandler;
import eu.pb4.polymer.api.block.PolymerBlockUtils;
import eu.pb4.polymer.api.resourcepack.PolymerRPUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.requireNonNull;

public class AdvancedHopperMod implements ModInitializer, ClientModInitializer {

    public static final String MOD_ID = "advancedhopper";

    public static final Identifier ADVANCED_HOPPER_ITEM_ID = new Identifier(MOD_ID, "advanced_hopper");
    public static final Identifier ADVANCED_HOPPER_BLOCK_ID = new Identifier(MOD_ID, "advanced_hopper");
    public static final Identifier ADVANCED_HOPPER_ENTITY_ID = new Identifier(MOD_ID, "advanced_hopper");
    public static final Identifier ADVANCED_HOPPER_SCREEN_ID = new Identifier(MOD_ID, "advanced_hopper");

    @Override
    public void onInitialize() {
        new Initializer();
    }

    @Override
    public void onInitializeClient() {
        new Initializer();
        HandledScreens.register(getScreenHandlerType(), AdvancedHopperScreen::new);
    }

    public static ScreenHandlerType<AdvancedHopperScreenHandler> getScreenHandlerType() {
        //noinspection unchecked
        return (ScreenHandlerType<AdvancedHopperScreenHandler>)
                requireNonNull(Registry.SCREEN_HANDLER.get(ADVANCED_HOPPER_SCREEN_ID));
    }

    public static BlockEntityType<AdvancedHopperBlockEntity> getBlockEntityType() {
        //noinspection unchecked
        return (BlockEntityType<AdvancedHopperBlockEntity>)
                requireNonNull(Registry.BLOCK_ENTITY_TYPE.get(ADVANCED_HOPPER_ENTITY_ID));
    }

    private static class Initializer {
        static {
            final Logger logger = LogManager.getLogger("Advanced Hopper");
            try {
                Registry.register(Registry.SCREEN_HANDLER, ADVANCED_HOPPER_SCREEN_ID, new ScreenHandlerType<>(AdvancedHopperScreenHandler::new));
                if (FabricLoader.getInstance().isModLoaded("polymer")) {
                    logger.info("Initializing Advanced Hopper with polymer.");
                    (new Registrar()).run();
                } else {
                    logger.info("Initializing Advanced Hopper.");
                    final AdvancedHopperBlock block = new AdvancedHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER).mapColor(MapColor.LIGHT_GRAY));
                    Registry.register(Registry.BLOCK, ADVANCED_HOPPER_BLOCK_ID, block);
                    Registry.register(
                            Registry.ITEM, ADVANCED_HOPPER_ITEM_ID,
                            new AdvancedHopperItem(block, new Item.Settings().group(ItemGroup.REDSTONE))
                    );
                    Registry.register(
                            Registry.BLOCK_ENTITY_TYPE, ADVANCED_HOPPER_ENTITY_ID,
                            FabricBlockEntityTypeBuilder.create(AdvancedHopperBlockEntity::new, block).build(null)
                    );
                }
            } catch (Exception e) {
                logger.catching(Level.ERROR, e);
                logger.error("Failed to initialize Advanced Hopper.");
            }
        }
    }

    private static class Registrar implements Runnable {
        @Override
        public void run() {
            PolymerRPUtils.addAssetSource(AdvancedHopperMod.MOD_ID);

            final PolymerAdvancedHopperBlock block = new PolymerAdvancedHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER).mapColor(MapColor.LIGHT_GRAY));
            Registry.register(Registry.BLOCK, ADVANCED_HOPPER_BLOCK_ID, block);
            Registry.register(
                    Registry.ITEM, ADVANCED_HOPPER_ITEM_ID,
                    new PolymerAdvancedHopperItem(block, new Item.Settings().group(ItemGroup.REDSTONE))
            );

            PolymerBlockUtils.registerBlockEntity(
                    Registry.register(
                            Registry.BLOCK_ENTITY_TYPE, AdvancedHopperMod.ADVANCED_HOPPER_ENTITY_ID,
                            FabricBlockEntityTypeBuilder.create(PolymerAdvancedHopperBlockEntity::new, block).build(null)
                    )
            );
        }
    }
}