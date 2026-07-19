package com.r4l.snipers_mini_game_mod.event;

import com.r4l.snipers_mini_game_mod.item.ItemManager;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelHandler {
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(ItemManager.FRF2, 0, 
            new ModelResourceLocation(ItemManager.FRF2.getRegistryName(), "inventory"));
    }
}
