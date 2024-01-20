package com.example.fastminecart;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityEventHandler {
    public static final ResourceLocation WORLD_CAP_LOCATION = new ResourceLocation(FastMinecart.MODID, "world");

    @SubscribeEvent
    public void attachWorldCapability(AttachCapabilitiesEvent<World> event) {
        event.addCapability(WORLD_CAP_LOCATION, new MinecartsCapProvider());
    }
}
