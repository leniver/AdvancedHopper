package com.leniver.advancedhopper;

import com.leniver.advancedhopper.block.AdvancedHopperBlock;
import com.leniver.advancedhopper.block.entity.AdvancedHopperBlockEntity;
import com.leniver.advancedhopper.screen.AdvancedHopperScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AdvancedHopperMod implements ModInitializer {
    public static final String MOD_ID = "advancedhopper";

    private static final Identifier ADVANCED_HOPPER_ID = new Identifier(MOD_ID, "advanced_hopper");
    public static final Block ADVANCED_HOPPER = new AdvancedHopperBlock(FabricBlockSettings.copyOf(Blocks.HOPPER).mapColor(MapColor.GOLD));
    public static final Item ADVANCED_HOPPER_ITEM = new BlockItem(ADVANCED_HOPPER, new Item.Settings().group(ItemGroup.REDSTONE));

    public static final BlockEntityType<AdvancedHopperBlockEntity> ADVANCED_HOPPER_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder.create(AdvancedHopperBlockEntity::new, ADVANCED_HOPPER).build(null);
    public static final ScreenHandlerType<AdvancedHopperScreenHandler> ADVANCED_HOPPER_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(ADVANCED_HOPPER_ID, AdvancedHopperScreenHandler::new);

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, ADVANCED_HOPPER_ID, ADVANCED_HOPPER);
        Registry.register(Registry.ITEM, ADVANCED_HOPPER_ID, ADVANCED_HOPPER_ITEM);

        Registry.register(Registry.BLOCK_ENTITY_TYPE, ADVANCED_HOPPER_ID, ADVANCED_HOPPER_BLOCK_ENTITY_TYPE);
    }
}