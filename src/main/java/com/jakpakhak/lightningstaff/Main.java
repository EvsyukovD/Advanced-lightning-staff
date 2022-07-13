package com.jakpakhak.lightningstaff;

import com.jakpakhak.lightningstaff.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Main {
    public static final String MODID = "lightningstaff";

    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
