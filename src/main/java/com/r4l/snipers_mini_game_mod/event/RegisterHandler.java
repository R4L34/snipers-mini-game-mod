package com.r4l.snipers_mini_game_mod.event;

import com.r4l.snipers_mini_game_mod.item.ItemManager;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisterHandler {
	
	@SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemManager.FRF2);
    }

}
