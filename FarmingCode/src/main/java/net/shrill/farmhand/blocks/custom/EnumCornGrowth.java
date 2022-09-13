package net.shrill.farmhand.blocks.custom;

// Code modified from BluSunrize's Immersive Engineering HempBlock class
// https://github.com/BluSunrize/ImmersiveEngineering/blob/1.18.2/src/main/java/blusunrize/immersiveengineering/common/blocks/plant/EnumHempGrowth.java

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.shrill.farmhand.Farmhand;

import java.util.Locale;

public enum EnumCornGrowth implements StringRepresentable
{
    BOTTOM0,
    BOTTOM1,
    BOTTOM2,
    BOTTOM3,
    BOTTOM4,
    TOP0;

    @Override
    public String getSerializedName()
    {
        return name().toLowerCase(Locale.ENGLISH);
    }

    public ResourceLocation getTextureName()
    {
        return new ResourceLocation(Farmhand.MOD_ID, "block/hemp/"+getSerializedName());
    }

    @Override
    public String toString()
    {
        return getSerializedName();
    }

    public EnumCornGrowth next()
    {
        return switch(this)
                {
                    // make 5 and 6th layer, TOP1 and TOP2 potentially?
                    case BOTTOM0 -> BOTTOM1;
                    case BOTTOM1 -> BOTTOM2;
                    case BOTTOM2 -> BOTTOM3;
                    case BOTTOM3 -> BOTTOM4;
                    case BOTTOM4, TOP0 -> this;
                };
    }

    public EnumCornGrowth getMin()
    {
        return TOP0==this?TOP0: BOTTOM0;
    }

    public EnumCornGrowth getMax()
    {
        return TOP0==this?TOP0: BOTTOM4;
    }
}
