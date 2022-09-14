package net.shrill.farmhand.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FHCreativeModeTab {

    public static final CreativeModeTab FARMHAND_CROPS = new CreativeModeTab("farmhandcropstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(FHItems.CORN_UNSHUCKED.get());
        }
    };
}
