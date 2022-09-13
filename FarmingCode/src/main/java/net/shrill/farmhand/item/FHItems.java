package net.shrill.farmhand.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shrill.farmhand.Farmhand;
import net.shrill.farmhand.blocks.FHBlocks;

public class FHItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Farmhand.MOD_ID);

    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new Item(new Item.Properties().tab(FHCreativeModeTab.FARMHAND_CROPS).food(FarmhandFoods.Corn)));

    public static final RegistryObject<Item> CORN_KERNAL = ITEMS.register("corn_kernal",
            () -> new ItemNameBlockItem(FHBlocks.CORN_CROP.get(),
                    new Item.Properties().tab(FHCreativeModeTab.FARMHAND_CROPS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
