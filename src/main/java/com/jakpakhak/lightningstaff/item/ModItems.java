package com.jakpakhak.lightningstaff.item;

import com.jakpakhak.lightningstaff.Main;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public static final RegistryObject<Item> FLASH_STAFF = ITEMS.register("lightning_staff", () -> new LightningStaff(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

}
