package net.shrill.farmhand.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shrill.farmhand.Farmhand;
import net.shrill.farmhand.blocks.custom.CaneCrop;
import net.shrill.farmhand.item.FHCreativeModeTab;
import net.shrill.farmhand.item.FHItems;

import java.util.function.Supplier;

public class FHBlocks {

    // REGISTERING METHODS
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Farmhand.MOD_ID);
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }


    // CORN TEST BOTTOM
    public static final RegistryObject<Block> CORN_TEST = registerBlock("corn_test2", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)
            .strength(6f).requiresCorrectToolForDrops()), FHCreativeModeTab.FARMHAND_CROPS);

    public static final RegistryObject<Block> CORN_MIDDLE = registerBlock("corn_middle", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)
            .strength(6f).requiresCorrectToolForDrops()), FHCreativeModeTab.FARMHAND_CROPS);

    public static final RegistryObject<Block> CORN_TOP = registerBlock("corn_top", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)
            .strength(6f).requiresCorrectToolForDrops()), FHCreativeModeTab.FARMHAND_CROPS);

    //CROP BLOCKS
    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop", () -> new CaneCrop(BlockBehaviour.Properties.copy(Blocks.SUGAR_CANE)));


    // BLOCK ITEM HELPER METHODS TO REGISTER BLOCKS AS ITEMS
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return FHItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }




}
