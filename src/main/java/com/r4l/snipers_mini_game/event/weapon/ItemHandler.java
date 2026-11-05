package com.r4l.snipers_mini_game.event.weapon;

import com.r4l.snipers_mini_game.Reference;
import com.r4l.snipers_mini_game.weapon.Items;
import com.r4l.snipers_mini_game.weapon.renderer.ModelFRF2;
import com.r4l.snipers_mini_game.weapon.renderer.RenderFRF2;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class ItemHandler {

    // Unique Identifier for your 3D Model
    public static final ModelLayerLocation FRF2_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Reference.MODID, "frf2"), "main");

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FRF2_LAYER, ModelFRF2::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            private RenderFRF2 renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (renderer == null) {
                    renderer = new RenderFRF2();
                }
                return renderer;
            }
        }, Items.FRF2.get());
    }
}