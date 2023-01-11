package net.zupancic.fulguratio.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zupancic.fulguratio.Fulguratio;

public class ItemsInit {
    //Contains all items from the mod
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Fulguratio.MODID);

    ///--------------------------------------
    public static final RegistryObject<Item> FIRE_ESSENCE = ITEMS.register("fire_essence", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WATER_ESSENCE = ITEMS.register("water_essence", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EARTH_ESSENCE = ITEMS.register("earth_essence", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AIR_ESSENCE = ITEMS.register("air_essence", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LIGHTNING_ESSENCE = ITEMS.register("lightning_essence", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FULGUR = ITEMS.register("fulgur", () -> new Item(new Item.Properties()));

}