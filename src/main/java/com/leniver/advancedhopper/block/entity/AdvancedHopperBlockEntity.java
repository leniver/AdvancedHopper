package com.leniver.advancedhopper.block.entity;

import java.util.stream.IntStream;

import com.leniver.advancedhopper.AdvancedHopperMod;
import com.leniver.advancedhopper.screen.AdvancedHopperScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class AdvancedHopperBlockEntity extends HopperBlockEntity implements SidedInventory {
    private static final int[] AVAILABLE_SLOTS = IntStream.range(0, 5).toArray();

    private Inventory filterInventory = new SimpleInventory(27);

    public AdvancedHopperBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public void scatterFilter() {
        ItemScatterer.spawn(this.getWorld(), this.getPos(), this.filterInventory);
    }

    public boolean isAcceptedByFilter(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return true;

        if (this.filterInventory.isEmpty()) return true;

        ItemStack filter = null;
        for (int i = 0; i < this.filterInventory.size(); i++) {
            filter = this.filterInventory.getStack(i);
            if (filter == null || filter.isEmpty()) continue;
            if (filter.getItem() == stack.getItem()) return true;
        }

        return false;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return AVAILABLE_SLOTS;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction direction) {
        return this.isAcceptedByFilter(stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.advanced_hopper");
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        for (int i = 0; i < this.filterInventory.size(); i++) {
            this.filterInventory.setStack(i, ItemStack.fromNbt(tag.getCompound("Filter")));
        }
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        for (int i = 0; i < this.filterInventory.size(); i++) {
            tag.put("Filter", this.filterInventory.getStack(i).writeNbt(new NbtCompound()));
        }
        super.writeNbt(tag);
    }

    @Override
    public BlockEntityType<?> getType() {
        return AdvancedHopperMod.ADVANCED_HOPPER_BLOCK_ENTITY_TYPE;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new AdvancedHopperScreenHandler(syncId, playerInventory, this, this.filterInventory);
    }
}
