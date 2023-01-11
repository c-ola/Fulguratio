package net.zupancic.fulguratio.blocks;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zupancic.fulguratio.Fulguratio;
import net.zupancic.fulguratio.items.ItemsInit;

public class BlocksInit {
    //Contains all blocks from the mod
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Fulguratio.MODID);

    //Every added block goes here (not sure about tile entities yet)
    //Each block has an item counter part
    public static final RegistryObject<Block> CRYSTALARIUM_BLOCK = BLOCKS.register("crystalarium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Item> CRYSTALARIUM_BLOCK_ITEM = ItemsInit.ITEMS.register("crystalarium_block_item", () -> new BlockItem(CRYSTALARIUM_BLOCK.get(), new Item.Properties()));


}
