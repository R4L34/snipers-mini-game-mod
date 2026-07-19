package com.r4l.snipers_mini_game_mod.event;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.r4l.snipers_mini_game_mod.item.ItemManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ScopeHandler {
	
	public static volatile boolean isAiming = false;
	
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Pre event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();

        if (mc.player != null && mc.player.getHeldItem(EnumHand.MAIN_HAND) != null && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == ItemManager.FRF2) {

            if (Mouse.isButtonDown(1) && FireHandler.RECOIL == 0) {
                isAiming = true;
                renderNativeScope(event.getResolution());
                event.setCanceled(true);
            } else {
                isAiming = false;
            }
            
        }
    }

    private static void renderNativeScope(ScaledResolution res) {
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();

        double cx = width / 2.0D;
        double cy = height / 2.0D;
        double radius = cy * 0.80D;

        // Use GlStateManager instead of direct GL11 where possible for state safety
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.disableCull();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        // DRAW MASK
        int segments = 120;
        double giantRadius = Math.max(width, height) * 2.0D;

        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

        for (int i = 0; i < segments; i++) {
            double angle1 = (i * 2.0D * Math.PI) / segments;
            double angle2 = ((i + 1) * 2.0D * Math.PI) / segments;

            // Inner vertices
            double x1 = cx + Math.cos(angle1) * radius;
            double y1 = cy + Math.sin(angle1) * radius;
            double x2 = cx + Math.cos(angle2) * radius;
            double y2 = cy + Math.sin(angle2) * radius;

            // Outer vertices
            double ox1 = cx + Math.cos(angle1) * giantRadius;
            double oy1 = cy + Math.sin(angle1) * giantRadius;
            double ox2 = cx + Math.cos(angle2) * giantRadius;
            double oy2 = cy + Math.sin(angle2) * giantRadius;

            // Add vertices: Black color (0,0,0) with 255 alpha (full opacity)
            buffer.pos(x1, y1, 0).color(0, 0, 0, 255).endVertex();
            buffer.pos(ox1, oy1, 0).color(0, 0, 0, 255).endVertex();
            buffer.pos(ox2, oy2, 0).color(0, 0, 0, 255).endVertex();
            buffer.pos(x2, y2, 0).color(0, 0, 0, 255).endVertex();
        }
        tessellator.draw();

        // DRAW BORDER AND CROSSHAIRS
        GlStateManager.color(0.0F, 0.0F, 0.0F, 1.0F);
        GL11.glLineWidth(4.0F);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (int i = 0; i < segments; i++) {
            double angle = (i * 2.0D * Math.PI) / segments;
            GL11.glVertex2d(cx + Math.cos(angle) * radius, cy + Math.sin(angle) * radius);
        }
        GL11.glEnd();

        GL11.glLineWidth(1.5F);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(0, cy); GL11.glVertex2d(width, cy);
        GL11.glVertex2d(cx, 0); GL11.glVertex2d(cx, height);
        GL11.glEnd();

        // RESTORE STATE
        GlStateManager.enableCull();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

}