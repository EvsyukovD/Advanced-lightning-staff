package com.jakpakhak.lightningstaff.utils;

import com.jakpakhak.lightningstaff.Main;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmlclient.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyInit {
    public static final KeyMapping rKeyMapping = registerKey("key_r", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_R);
    public static final KeyMapping tKeyMapping = registerKey("key_t", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_T);
    public static final KeyMapping yKeyMapping = registerKey("key_y", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_Y);

    private static KeyMapping registerKey(String name, String category, int keycode) {
        final KeyMapping key = new KeyMapping("key." + Main.MODID + "." + name, keycode, category);
        return key;
    }

    @SubscribeEvent
    public static void registerKeyBindings(final FMLCommonSetupEvent event) {
        ClientRegistry.registerKeyBinding(rKeyMapping);
        ClientRegistry.registerKeyBinding(tKeyMapping);
        ClientRegistry.registerKeyBinding(yKeyMapping);
    }

}
