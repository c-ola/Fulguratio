package net.zupancic.fulguratio.items;


import java.util.ArrayList;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zupancic.fulguratio.Fulguratio;
import net.zupancic.fulguratio.armor.ModArmorMaterial;
import net.zupancic.fulguratio.tools.ModTieredItem;

public class ModItems {
    //Contains all items from the mod
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Fulguratio.MODID);

    //--------------------------------------
    public static final RegistryObject<Item> FIRE_ESSENCE = ITEMS.register("fire_essence", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WATER_ESSENCE = ITEMS.register("water_essence", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EARTH_ESSENCE = ITEMS.register("earth_essence", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AIR_ESSENCE = ITEMS.register("air_essence", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LIGHTNING_ESSENCE = ITEMS.register("lightning_essence", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FULGURIUM = ITEMS.register("fulgurium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FULGURIUM_AWAKENED = ITEMS.register("fulgurium_awakened", () -> new Item(new Item.Properties()));

    public static final RegistryObject<ArmorItem> FULGURIUM_HEAD = ITEMS.register("fulgurium_helmet", () -> 
        new ArmorItem(ModArmorMaterial.FULGURIUM, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<ArmorItem> FULGURIUM_CHESTPLATE = ITEMS.register("fulgurium_chestplate", () -> 
        new ArmorItem(ModArmorMaterial.FULGURIUM, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<ArmorItem> FULGURIUM_LEGGINGS = ITEMS.register("fulgurium_leggings", () -> 
        new ArmorItem(ModArmorMaterial.FULGURIUM, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> FULGURIUM_BOOTS = ITEMS.register("fulgurium_boots", () -> 
        new ArmorItem(ModArmorMaterial.FULGURIUM, EquipmentSlot.FEET, new Item.Properties()));

    public static final RegistryObject<SwordItem> FULGURIUM_SWORD = ITEMS.register("fulgurium_sword", () ->
        new SwordItem(ModTieredItem.FULGURIUM, 3, 1.0f, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> FULGURIUM_PICKAXE = ITEMS.register("fulgurium_pickaxe", () ->
        new PickaxeItem(ModTieredItem.FULGURIUM, 1, 1.5f, new Item.Properties()));
    public static final RegistryObject<AxeItem> FULGURIUM_AXE = ITEMS.register("fulgurium_axe", () ->
        new AxeItem(ModTieredItem.FULGURIUM, 4, 0.5f, new Item.Properties()));
    public static final RegistryObject<ShovelItem> FULGURIUM_SHOVEL = ITEMS.register("fulgurium_shovel", () ->
        new ShovelItem(ModTieredItem.FULGURIUM, 1, 1.5f, new Item.Properties()));
    public static final RegistryObject<HoeItem> FULGURIUM_HOE = ITEMS.register("fulgurium_hoe", () ->
        new HoeItem(ModTieredItem.FULGURIUM, 1, 1.5f, new Item.Properties()));


    public static ArrayList<ItemStack> ESSENCES = new ArrayList<ItemStack>();

    public static void register(IEventBus eventBus){
        //ModBlocks.BLOCKS.getEntries().forEach((block) -> ITEMS.register(block.getId().getNamespace(), ()-> new BlockItem(block.get(), new Item.Properties())));
        ITEMS.register(eventBus);
        ESSENCES.add(FIRE_ESSENCE.get().getDefaultInstance());
        ESSENCES.add(WATER_ESSENCE.get().getDefaultInstance());
        ESSENCES.add(EARTH_ESSENCE.get().getDefaultInstance());
        ESSENCES.add(AIR_ESSENCE.get().getDefaultInstance());
        ESSENCES.add(LIGHTNING_ESSENCE.get().getDefaultInstance());
    }

    public static void addItemsToCreativeTab(Output populator){
        ITEMS.getEntries().forEach((n) -> {
            populator.accept(n.get());  
        });
    }
}