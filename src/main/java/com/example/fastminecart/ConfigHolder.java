package com.example.fastminecart;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigHolder
{
    public static class Common
    {
        private static final float defaultSpeed = 1f;

        public final ForgeConfigSpec.DoubleValue speed;

        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("FastMinecarft");
            this.speed = builder.comment("This is the multiplier of the minecart speed. Default is 1.")
                    .worldRestart()
                    .defineInRange("Speed", defaultSpeed, 1f, 1000f);
            builder.pop();
        }
    }

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static
    {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }
}