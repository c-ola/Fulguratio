package net.zupancic.fulguratio.menu;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zupancic.fulguratio.Fulguratio;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Fulguratio.MODID);
    public static final RegistryObject<MenuType<EssenceCraftingTableMenu>> ESSENCE_CRAFTING_TABLE_MENU = 
        MENU_TYPES.register("essence_crafting_table_menu", () -> new MenuType<>(EssenceCraftingTableMenu::getClientMenu));
}
