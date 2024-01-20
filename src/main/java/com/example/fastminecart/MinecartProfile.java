package com.example.fastminecart;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Objects;
import java.util.UUID;

public class MinecartProfile implements INBTSerializable<CompoundNBT> {

    public float cachedSpeed;
    public UUID id;

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putUUID("id", id);
        nbt.putFloat("s", cachedSpeed);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.id = nbt.getUUID("id");
        this.cachedSpeed = nbt.getFloat("s");
    }

    @Override
    public int hashCode() {
        return Objects.hash(cachedSpeed, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MinecartProfile)) return false;
        MinecartProfile obj1 = (MinecartProfile) obj;
        return id == obj1.id && obj1.cachedSpeed == cachedSpeed;
    }
}
