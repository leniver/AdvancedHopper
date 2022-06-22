package com.leniver.advancedhopper.polymer;

import com.leniver.advancedhopper.block.AdvancedHopperBlock;
import eu.pb4.polymer.api.block.PolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HopperBlock;

public class PolymerAdvancedHopperBlock extends AdvancedHopperBlock implements PolymerBlock {

    public PolymerAdvancedHopperBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Block getPolymerBlock(BlockState state) {
        return Blocks.HOPPER;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return Blocks.HOPPER.getDefaultState()
                .with(HopperBlock.FACING, state.get(HopperBlock.FACING))
                .with(HopperBlock.ENABLED, state.get(HopperBlock.ENABLED));
    }
}
