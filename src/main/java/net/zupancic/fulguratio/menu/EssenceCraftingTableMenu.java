package net.zupancic.fulguratio.menu;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.zupancic.fulguratio.Fulguratio;
import net.zupancic.fulguratio.block_entity.EssenceCraftingTableBlockEntity;
import net.zupancic.fulguratio.blocks.ModBlocks;
import net.zupancic.fulguratio.items.ModItems;
import net.zupancic.fulguratio.items.ModSlotItemHandler;

public class EssenceCraftingTableMenu extends AbstractContainerMenu{

    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;

    protected EssenceCraftingTableMenu(int containerId, Inventory playerInventory, IItemHandler slots, BlockPos pos, ContainerData data) {
        super(ModMenuTypes.ESSENCE_CRAFTING_TABLE_MENU.get(), containerId);
        this.levelAccess = ContainerLevelAccess.create(playerInventory.player.getLevel(), pos);
        this.data = data;
        ArrayList<ItemStack> ESSENCES = new ArrayList<ItemStack>();
        ESSENCES.add(ModItems.FIRE_ESSENCE.get().getDefaultInstance());
        ESSENCES.add(ModItems.WATER_ESSENCE.get().getDefaultInstance());
        ESSENCES.add(ModItems.EARTH_ESSENCE.get().getDefaultInstance());
        ESSENCES.add(ModItems.AIR_ESSENCE.get().getDefaultInstance());
        ESSENCES.add(ModItems.LIGHTNING_ESSENCE.get().getDefaultInstance());

        final int[] activatorSlotPos = {79, 40}; 
        final int[] essenceSlotPos = {79, 76}; 
        final int[] outputSlotPos = {97, 58};

        //predicate my ass
        addSlot(new ModSlotItemHandler(slots, 0, activatorSlotPos[0], activatorSlotPos[1]));
        addSlot(new ModSlotItemHandler(slots, 1, essenceSlotPos[0], essenceSlotPos[1]));
        addSlot(new ModSlotItemHandler(slots, 2, outputSlotPos[0], outputSlotPos[1], Predicates.alwaysFalse()));

        final int slotSizePlus2 = 18;
        final int startX = 8;
        final int startY = 84;
        final int hotbarY = 142;


        for(int row = 0; row < 3; ++row){
            for(int col = 0; col < 9; ++col){
                addSlot(new Slot(playerInventory, col + row * 9 + 9, startX + col * slotSizePlus2, startY + row * slotSizePlus2));
            }
        }
        for(int col = 0; col < 9; ++col){
            addSlot(new Slot(playerInventory, col, startX + col * slotSizePlus2, hotbarY));
        }

        this.addDataSlots(this.data);
    }

    public static EssenceCraftingTableMenu getClientMenu(int id, Inventory playerInventory){
        return new EssenceCraftingTableMenu(id, playerInventory, new ItemStackHandler(3), BlockPos.ZERO, new SimpleContainerData(3));
    }

    public static MenuConstructor getServerMenu(EssenceCraftingTableBlockEntity blockEntity, BlockPos pos){
        return (id, playerInventory, player) -> new EssenceCraftingTableMenu(id, playerInventory, blockEntity.getInventory(), pos, blockEntity.getContainerData());
    }


    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack current = slot.getItem();
            itemstack = current.copy();
            if (index < 3) {
                if (!this.moveItemStackTo(current, 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(current, 0, 2, false)) {
                return ItemStack.EMPTY;
            }

            if (current.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.levelAccess, player, ModBlocks.ESSENCE_CRAFTING_TABLE.get());
    }

    public ContainerData getData(){
        return data;
    }
    
}
