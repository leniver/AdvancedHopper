package com.leniver.advancedhopper.polymer;

import com.leniver.advancedhopper.block.AdvancedHopperItem;
import eu.pb4.polymer.api.item.PolymerItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class PolymerAdvancedHopperItem extends AdvancedHopperItem implements PolymerItem {

    public PolymerAdvancedHopperItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.HOPPER;
    }

    @Override
    public Text getName() {
        return Text.translatable("Advanced Hopper");
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable("Advanced Hopper");
    }
}
