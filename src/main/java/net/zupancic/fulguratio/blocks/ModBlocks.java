package net.zupancic.fulguratio.blocks;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zupancic.fulguratio.Fulguratio;
import net.zupancic.fulguratio.items.ModItems;

public class ModBlocks {
    //Contains all blocks from the mod
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Fulguratio.MODID);
    public static final Material HARD_ESSENCE = (new Material.Builder(MaterialColor.STONE)).build();

    //Every added block goes here (not sure about tile entities yet)
    //Each block has an item counter part
    public static final RegistryObject<Block> CRYSTALARIUM_BLOCK = 
        BLOCKS.register("crystalarium_block", () -> new Block(BlockBehaviour.Properties.of(Material.GLASS)));
    public static final RegistryObject<Item> CRYSTALARIUM_BLOCK_ITEM = 
        ModItems.ITEMS.register("crystalarium_block", () -> new BlockItem(CRYSTALARIUM_BLOCK.get(), new Item.Properties()));
    
    public static final RegistryObject<Block> FULGURIUM_BLOCK = 
        BLOCKS.register("fulgurium_block", () -> new Block(BlockBehaviour.Properties.of(HARD_ESSENCE).strength(1f)));
    public static final RegistryObject<Item> FULGURIUM_BLOCK_ITEM = 
        ModItems.ITEMS.register("fulgurium_block", () -> new BlockItem(FULGURIUM_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Block> FULGURIUM_ORE = 
        BLOCKS.register("fulgurium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.7f), UniformInt.of(3, 7)));
    public static final RegistryObject<Item> FULGURIUM_ORE_ITEM = 
        ModItems.ITEMS.register("fulgurium_ore", () -> new BlockItem(FULGURIUM_ORE.get(), new Item.Properties()));


    public static final RegistryObject<Block> ESSENCE_CRAFTING_TABLE =
        BLOCKS.register("essence_crafting_table", () -> new EssenceCraftingTableBlock(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Item> ESSENCE_CRAFTING_TABLE_ITEM = 
        ModItems.ITEMS.register("essence_crafting_table", () -> new BlockItem(ESSENCE_CRAFTING_TABLE.get(), new Item.Properties()));
    
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    

}
