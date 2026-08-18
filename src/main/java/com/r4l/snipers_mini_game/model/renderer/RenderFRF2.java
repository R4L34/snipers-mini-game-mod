package com.r4l.snipers_mini_game.model.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.r4l.snipers_mini_game.Reference;
import com.r4l.snipers_mini_game.event.ItemHandler;
import com.r4l.snipers_mini_game.event.ScopeHandler;
import com.r4l.snipers_mini_game.model.ModelFRF2;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class RenderFRF2 extends BlockEntityWithoutLevelRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Reference.MODID, "textures/modeltextures/frf2.png");
    private ModelFRF2 model;

    public RenderFRF2() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    private ModelFRF2 getModel() {
        if (this.model == null) {
            this.model = new ModelFRF2(Minecraft.getInstance().getEntityModels().bakeLayer(ItemHandler.FRF2_LAYER));
        }
        return this.model;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        boolean isFirstPerson = displayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND || displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND;

        if (isFirstPerson) {
            // Note: Bring your ScopeHandler.isAiming logic back here if you still have it!
            float scale = 0.5f;
            if (ScopeHandler.isAiming) scale = 0.0f;
            poseStack.translate(0.75f, 0f, -1f);
            poseStack.scale(scale, scale, scale);
            poseStack.mulPose(Axis.ZP.rotationDegrees(180));
        } else if (displayContext == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || displayContext == ItemDisplayContext.THIRD_PERSON_LEFT_HAND) {
            float scale = 0.15F;
            poseStack.translate(0.5f, 0.5f, 0.5f);
            poseStack.scale(scale, scale, scale);
            poseStack.mulPose(Axis.ZP.rotationDegrees(180));
        } else {
            // Fallback for GUI, Item Frames, Ground etc.
            float scale = 0.15F;
            poseStack.translate(0.5f, 0.5f, 0.5f);
            poseStack.scale(scale, scale, scale);
            poseStack.mulPose(Axis.ZP.rotationDegrees(180));
        }

        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(buffer, RenderType.entityCutoutNoCull(TEXTURE), true, stack.hasFoil());
        getModel().renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 0xFFFFFFFF);

        poseStack.popPose();
    }
}
