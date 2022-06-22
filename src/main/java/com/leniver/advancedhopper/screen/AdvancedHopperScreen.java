package com.leniver.advancedhopper.screen;

import com.leniver.advancedhopper.AdvancedHopperMod;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AdvancedHopperScreen extends HandledScreen<AdvancedHopperScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(AdvancedHopperMod.MOD_ID, "textures/gui/container/advanced_hopper.png");

    public AdvancedHopperScreen(AdvancedHopperScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.passEvents = false;

        this.backgroundHeight = 191;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);

        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        this.drawTexture(matrices, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);

        // Render the filter slot placeholder
        Slot[] filterSlots = this.handler.getFilterSlots();
        Slot filterSlot;
        for (int i = 0; i < filterSlots.length; i++) {
            filterSlot = filterSlots[i];
            if (!filterSlot.hasStack()) {
                this.drawFilterIcon(matrices, filterSlot);
            }
        }
    }

    private void drawFilterIcon(MatrixStack matrices, Slot slot) {
        this.drawTexture(matrices, this.x + slot.x, this.y + slot.y, this.backgroundWidth, 0, 16, 16);
    }
}