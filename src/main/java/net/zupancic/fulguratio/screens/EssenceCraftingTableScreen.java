package net.zupancic.fulguratio.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.zupancic.fulguratio.Fulguratio;
import net.zupancic.fulguratio.menu.EssenceCraftingTableMenu;

public class EssenceCraftingTableScreen extends AbstractContainerScreen<EssenceCraftingTableMenu>{
    //might have to create mod edit box??
    private static final ResourceLocation TEXTURE = new ResourceLocation(Fulguratio.MODID, "textures/gui/essence_craftingtable.png");
    private boolean widthTooNarrow;

    //figure this shit out later
    //private final RecipeType<? extends AbstractCookingRecipe> recipeType;

    public EssenceCraftingTableScreen(EssenceCraftingTableMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    public void render(PoseStack p_97858_, int p_97859_, int p_97860_, float p_97861_) {
        this.renderBackground(p_97858_);
        super.render(p_97858_, p_97859_, p_97860_, p_97861_);        
        this.renderTooltip(p_97858_, p_97859_, p_97860_);
    }
    
    @Override
    protected void renderBg(PoseStack p_97853_, float p_97854_, int p_97855_, int p_97856_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(p_97853_, i, j, 0, 0, this.imageWidth, this.imageHeight);
        // if (this.menu.isLit()) {
        //    int k = this.menu.getLitProgress();
        //    this.blit(p_97853_, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        // }
  
        // int l = this.menu.getBurnProgress();
        this.blit(p_97853_, i + 79, j + 34, 176, 14, 1, 16);
     }

    @Override
    public boolean mouseClicked(double p_97834_, double p_97749_, int p_97750_) {
        return this.widthTooNarrow ? true : super.mouseClicked(p_97834_, p_97749_, p_97750_);
    }

}
