package com.r4l.snipers_mini_game_mod.model.renderer;


import com.r4l.snipers_mini_game_mod.event.ScopeHandler;
import com.r4l.snipers_mini_game_mod.model.ModelFRF2;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;


public class RenderFRF2 extends TileEntityItemStackRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation("snipers_mini_game_mod", "modeltextures/frf2.png");

    protected ModelFRF2 model;

    public RenderFRF2() {
        this.model = new ModelFRF2();
    }

    @Override
    public void renderByItem(ItemStack itemStack) {
    	
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;

        if (player == null) return;

        // TEISR doesn't pass 'ItemRenderType', so we check perspective and hands manually
        boolean isFirstPerson = mc.gameSettings.thirdPersonView == 0 && (player.getHeldItemMainhand() == itemStack || player.getHeldItemOffhand() == itemStack) && mc.currentScreen == null;

        // MAIN WEAPON RENDER
        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(TEXTURE);

        if (isFirstPerson) {
          //First Person
        	float scale;
        	if(ScopeHandler.isAiming) {
        		scale = 0.0f;
        	} else {
        		scale = 0.5f;
        	}
            GlStateManager.translate(0.75f, 0f, -1f);
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.rotate(180, 0, 0, 1);
        } else {
        	// Third Person
            float scale = 0.15F;
            GlStateManager.translate(0.5f, 0.5f, 0.5f);
            GlStateManager.scale(scale, scale, scale);
            GlStateManager.rotate(180, 0, 0, 1);
        }
        this.model.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();

        
    }

}