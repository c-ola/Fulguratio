package net.zupancic.fulguratio.blocks;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.zupancic.fulguratio.block_entity.EssenceCraftingTableBlockEntity;
import net.zupancic.fulguratio.block_entity.ModBlockEntityType;
import net.zupancic.fulguratio.menu.EssenceCraftingTableMenu;

public class EssenceCraftingTableBlock extends Block implements EntityBlock {

    //private final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public EssenceCraftingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EssenceCraftingTableBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
                return level.isClientSide() ? null : ($0, $1, $2, blockEntity) -> {
            if(blockEntity instanceof EssenceCraftingTableBlockEntity essence_crafting_table) {
                essence_crafting_table.tick();
            }
        };
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()){          
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof EssenceCraftingTableBlockEntity essenceCraftingTable){
                MenuConstructor menuConstructor = EssenceCraftingTableMenu.getServerMenu(essenceCraftingTable, pos);
                SimpleMenuProvider provider = new SimpleMenuProvider(menuConstructor, EssenceCraftingTableBlockEntity.TITLE);
                NetworkHooks.openScreen((ServerPlayer) player, provider, pos);


                // if(((EssenceCraftingTableBlockEntity)blockEntity).isReady()){
                //     world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 1.0F);
                //     ((EssenceCraftingTableBlockEntity) blockEntity).toggle();
                    

                //     return InteractionResult.SUCCESS;
                // }
            }
        }
        return InteractionResult.sidedSuccess(!level.isClientSide);
    }
}