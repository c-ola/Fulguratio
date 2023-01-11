 package net.zupancic.fulguratio;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.zupancic.fulguratio.blocks.BlocksInit;
import net.zupancic.fulguratio.items.ItemsInit;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Fulguratio.MODID)
public class Fulguratio
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "fulguratio";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Fulguratio()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BlocksInit.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ItemsInit.ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event){
        LOGGER.info("common setup or something");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){

        }
    }

}

// public static final CreativeModeTab TAB = CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0).title(Component.translatable("itemGroup.SomeModsStuff")).icon(
//     () -> { 
//         return new ItemStack(Blocks.BRICKS);
//     }).displayItems((a, b, c) -> {
//         b.accept(ItemsInit.FIRE_ESSENCE.get());
//     }).build();
// public static final CreativeModeTab SOME_MODS_TAB = new CreativeModeTabs(MODID){
//     @Override
//     public @NotNull ItemStack makeIcon(){
//         return ItemsInit.FIRE_ESSENCE_ITEM.get().getDefaultInstance();
//     }

// };
