package com.r4l.snipers_mini_game;

import com.r4l.snipers_mini_game.weapon.Items;
import com.r4l.snipers_mini_game.weapon.WeaponSounds;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(Reference.MODID)
public class Main {

    public Main(IEventBus modEventBus) {
        // Register Deferred Registers
        WeaponSounds.SOUNDS.register(modEventBus);
        Items.ITEMS.register(modEventBus);

        // Register Creative Tab Event
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(new ItemStack(Items.FRF2));
        }
    }
}