package com.r4l.snipers_mini_game.event;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.r4l.snipers_mini_game.Reference;
import com.r4l.snipers_mini_game.weapon.Items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import org.joml.Matrix4f;

@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class ScopeHandler {
    
    public static volatile boolean isAiming = false;
    
    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiLayerEvent.Pre event) {
        // Only intercept when the game is trying to draw the crosshair
        if (!event.getName().equals(VanillaGuiLayers.CROSSHAIR)) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        if (mc.player != null && mc.player.getMainHandItem().is(Items.FRF2.get())) {
        	event.setCanceled(true);
            // Replaces Mouse.isButtonDown(1) with modern key bindings
            if (mc.options.keyUse.isDown() && FireHandler.RECOIL == 0) {
                isAiming = true;
                
                int width = mc.getWindow().getGuiScaledWidth();
                int height = mc.getWindow().getGuiScaledHeight();
                
                renderNativeScope(event.getGuiGraphics(), width, height);
               // event.setCanceled(true); // Hide the default crosshair
            } else {
                isAiming = false;
            }
            
        } else {
            // Reset aiming if we switch items
            isAiming = false;
        }
    }

    private static void renderNativeScope(GuiGraphics guiGraphics, int width, int height) {
        double cx = width / 2.0D;
        double cy = height / 2.0D;
        double radius = cy * 0.80D;

        // Modern GlStateManager = RenderSystem
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        Tesselator tesselator = Tesselator.getInstance();
        Matrix4f pose = guiGraphics.pose().last().pose();

        // 1. DRAW MASK (QUADS)
        BufferBuilder buffer = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        int segments = 120;
        double giantRadius = Math.max(width, height) * 2.0D;

        for (int i = 0; i < segments; i++) {
            double angle1 = (i * 2.0D * Math.PI) / segments;
            double angle2 = ((i + 1) * 2.0D * Math.PI) / segments;

            float x1 = (float) (cx + Math.cos(angle1) * radius);
            float y1 = (float) (cy + Math.sin(angle1) * radius);
            float x2 = (float) (cx + Math.cos(angle2) * radius);
            float y2 = (float) (cy + Math.sin(angle2) * radius);

            float ox1 = (float) (cx + Math.cos(angle1) * giantRadius);
            float oy1 = (float) (cy + Math.sin(angle1) * giantRadius);
            float ox2 = (float) (cx + Math.cos(angle2) * giantRadius);
            float oy2 = (float) (cy + Math.sin(angle2) * giantRadius);

            // Add vertices: Black color (0,0,0) with 255 alpha (full opacity)
            buffer.addVertex(pose, x1, y1, 0).setColor(0, 0, 0, 255);
            buffer.addVertex(pose, ox1, oy1, 0).setColor(0, 0, 0, 255);
            buffer.addVertex(pose, ox2, oy2, 0).setColor(0, 0, 0, 255);
            buffer.addVertex(pose, x2, y2, 0).setColor(0, 0, 0, 255);
        }
        BufferUploader.drawWithShader(buffer.buildOrThrow());

        // 2. DRAW INNER CROSSHAIRS
        // We use GuiGraphics.fill() for perfect, anti-aliased 1px / 2px straight lines!
        guiGraphics.fill(0, (int)cy, width, (int)cy + 1, 0xFF000000); // Horizontal
        guiGraphics.fill((int)cx, 0, (int)cx + 1, height, 0xFF000000); // Vertical

        // 3. DRAW CIRCULAR BORDER (LINE_STRIP)
        RenderSystem.lineWidth(4.0F); // (Note: Many modern GPUs will clamp this to 1.0F anyway)
        buffer = tesselator.begin(VertexFormat.Mode.LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);
        
        // Use <= instead of < to perfectly close the circle loop
        for (int i = 0; i <= segments; i++) {
            double angle = (i * 2.0D * Math.PI) / segments;
            float px = (float) (cx + Math.cos(angle) * radius);
            float py = (float) (cy + Math.sin(angle) * radius);
            buffer.addVertex(pose, px, py, 0).setColor(0, 0, 0, 255);
        }
        BufferUploader.drawWithShader(buffer.buildOrThrow());

        // RESTORE STATE
        RenderSystem.lineWidth(1.0F);
        RenderSystem.enableCull();
    }
}