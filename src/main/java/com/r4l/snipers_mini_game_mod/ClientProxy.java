package com.r4l.snipers_mini_game_mod;


import com.r4l.snipers_mini_game_mod.event.ArmHandler;
import com.r4l.snipers_mini_game_mod.event.FOVHandler;
import com.r4l.snipers_mini_game_mod.event.FireHandler;
import com.r4l.snipers_mini_game_mod.event.ModelHandler;
import com.r4l.snipers_mini_game_mod.event.ScopeHandler;
import com.r4l.snipers_mini_game_mod.event.SensitivityHandler;
import com.r4l.snipers_mini_game_mod.item.ItemManager;
import com.r4l.snipers_mini_game_mod.model.renderer.RenderFRF2;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	
	public void preInit(FMLPreInitializationEvent event) 	{
        super.preInit(event);
        ItemManager.FRF2.setTileEntityItemStackRenderer(new RenderFRF2());
        MinecraftForge.EVENT_BUS.register(new ScopeHandler());
        MinecraftForge.EVENT_BUS.register(new ModelHandler());
        MinecraftForge.EVENT_BUS.register(new SensitivityHandler());
        MinecraftForge.EVENT_BUS.register(new FOVHandler());
        MinecraftForge.EVENT_BUS.register(new FireHandler());
        MinecraftForge.EVENT_BUS.register(new ArmHandler());
        
        
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
