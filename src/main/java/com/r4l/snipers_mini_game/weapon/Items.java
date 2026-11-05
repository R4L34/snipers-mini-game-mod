package com.r4l.snipers_mini_game.weapon;
import com.r4l.snipers_mini_game.Reference;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class Items {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MODID);

    // Pass properties to the FRF2 Item class
    public static final DeferredHolder<Item, FRF2> FRF2 = ITEMS.registerItem("frf2", FRF2::new);
}