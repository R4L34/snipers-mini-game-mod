package com.r4l.snipers_mini_game_mod;


import com.r4l.snipers_mini_game_mod.event.RegisterHandler;
import com.r4l.snipers_mini_game_mod.event.server.WeaponHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new RegisterHandler());
		MinecraftForge.EVENT_BUS.register(new Sounds());
		MinecraftForge.EVENT_BUS.register(new WeaponHandler());
	}
	

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
