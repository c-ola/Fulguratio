 package net.zupancic.fulguratio;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.zupancic.fulguratio.blocks.ModBlocks;
import net.zupancic.fulguratio.items.ModItems;


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


        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.ITEMS.register(modEventBus);

        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.BLOCKS.register(modEventBus);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        //creative mode tab
        modEventBus.addListener(this::buildContents);
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
    
    //Sets up a creative mode tab for the mod, contains all the mods items and blocks
    @SubscribeEvent
    public void buildContents(CreativeModeTabEvent.Register event){
    event.registerCreativeModeTab(new ResourceLocation(MODID, "fulguratio"), builder ->
            builder.title(Component.translatable("itemGroup.fulguratio.fulguratioTab"))
            .icon(() -> new ItemStack(ModItems.FULGURIUM.get()))
            .displayItems((enabledFlags, populator, hasPermissions) -> {
                ModItems.addItemsToCreativeTab(populator);

            })
        );   
    }

}


