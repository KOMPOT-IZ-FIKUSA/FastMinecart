package com.example.fastminecart;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.*;

public class MinecartsCap implements INBTSerializable<CompoundNBT>, Capability.IStorage<MinecartsCap> {

    public final HashMap<UUID, Float> minecartsTicks = new HashMap<>();

    public MinecartsCap() {

    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        int i = 0;
        for (Map.Entry<UUID, Float> pair: minecartsTicks.entrySet()) {
            nbt.putUUID("id" + i, pair.getKey());
            nbt.putFloat("val" + i, pair.getValue());
            i += 1;
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        minecartsTicks.clear();
        if (nbt == null) return;
        int i = 0;
        while (nbt.contains("id" + i)) {
            minecartsTicks.put(nbt.getUUID("id" + i), nbt.getFloat("val" + i));
            i++;
        }
    }

    @Override
    public INBT writeNBT(Capability<MinecartsCap> capability, MinecartsCap iOverworldCap, Direction direction) {
        return ((MinecartsCap) iOverworldCap).serializeNBT();
    }

    @Override
    public void readNBT(Capability<MinecartsCap> capability, MinecartsCap iOverworldCap, Direction direction, INBT inbt) {
        ((MinecartsCap) iOverworldCap).deserializeNBT((CompoundNBT) inbt);
    }
}
