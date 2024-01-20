package com.example.fastminecart;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.UUID;

public class EventsHandler {


    @SubscribeEvent
    public void onMinecartJoinWorld(EntityJoinWorldEvent event) {
        if (event.getWorld() == null) return;
        if (event.getEntity() == null) return;
        if (event.getWorld().isClientSide) return;
        if (event.getEntity() instanceof MinecartEntity) {
            MinecartsCap cap = MinecartsCapProvider.get(event.getWorld());
            if (cap == null) return;
            UUID id = event.getEntity().getUUID();
            cap.minecartsTicks.put(id, 0f);
        }
    }

    @SubscribeEvent
    public void onMinecartLeaveWorld(EntityLeaveWorldEvent event) {
        if (event.getWorld() == null) return;
        if (event.getEntity() == null) return;
        if (event.getWorld().isClientSide) return;
        if (event.getEntity() instanceof MinecartEntity) {
            MinecartsCap cap = MinecartsCapProvider.get(event.getWorld());
            if (cap == null) return;
            UUID id = event.getEntity().getUUID();
            cap.minecartsTicks.remove(id);
        }
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.world == null) return;
        if (event.world.isClientSide) return;
        if (!(event.world instanceof ServerWorld))  return;
        ServerWorld world = (ServerWorld) event.world;
        if (event.phase != TickEvent.Phase.END) return;
        HashMap<UUID, Float> ticks = MinecartsCapProvider.get(world).minecartsTicks;
        if (ticks == null) return;
        for (UUID id: ticks.keySet()) {
            if (id == null) continue;
            Entity entity = world.getEntity(id);
            if (entity == null) continue;
            if (!(entity instanceof MinecartEntity)) continue;

            float value = ticks.get(id);
            while (value >= 1) {
                entity.tick();
                value -= 1;
            }
            float delta = ConfigHolder.COMMON.speed.get().floatValue() - 1;
            if (delta < 0) return;
            ticks.put(id, value + delta);
        }
    }


}
