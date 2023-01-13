package net.zupancic.fulguratio.block_entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zupancic.fulguratio.Fulguratio;
import net.zupancic.fulguratio.blocks.ModBlocks;

public class ModBlockEntityType{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Fulguratio.MODID);
    //public static final BlockEntityType<EssenceCraftingTableBlockEntity> ESSENCE_CRAFTING_TABLE = register("furnace", BlockEntityType.Builder.of(EssenceCraftingTableBlockEntity::new, Blocks.FURNACE));

    public static final RegistryObject<BlockEntityType<EssenceCraftingTableBlockEntity>> ESSENCE_CRAFTING_TABLE =     
        BLOCK_ENTITY_TYPES.register("essence_crafting_table",  () -> BlockEntityType.Builder.of(EssenceCraftingTableBlockEntity::new, ModBlocks.ESSENCE_CRAFTING_TABLE.get()).build(null));
}
