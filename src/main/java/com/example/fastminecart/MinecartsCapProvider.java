package com.example.fastminecart;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MinecartsCapProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(MinecartsCap.class)
    public static final Capability<MinecartsCap> WORLD_CAP = null;
    private LazyOptional<MinecartsCap> instance = LazyOptional.of(() -> WORLD_CAP.getDefaultInstance());

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction direction) {
        return capability == WORLD_CAP ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) WORLD_CAP.getStorage().writeNBT(WORLD_CAP, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        WORLD_CAP.getStorage().readNBT(WORLD_CAP, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt);
    }

    public static MinecartsCap get(@Nonnull World world) {
        return world.getCapability(WORLD_CAP).orElse(new MinecartsCap());
    }
}
