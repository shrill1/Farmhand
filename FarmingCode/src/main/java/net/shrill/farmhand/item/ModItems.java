package net.shrill.farmhand.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import net.shrill.farmhand.Farmhand;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Farmhand.MOD_ID);

    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARMHAND_CROPS).food(Foods.Corn)));

    public static final RegistryObject<Item> CORN_KERNALS = ITEMS.register("corn_kernal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARMHAND_CROPS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
