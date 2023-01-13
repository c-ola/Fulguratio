package net.zupancic.fulguratio.items;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

//Default Value for predication is true
public class ModSlotItemHandler extends SlotItemHandler{
    private final Predicate<ItemStack> validator;

    public ModSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, Predicate<ItemStack> validator){
        super(itemHandler, index, xPosition, yPosition);
        this.validator = validator;
    }

    public ModSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition){
        this(itemHandler, index, xPosition, yPosition, Predicates.alwaysTrue());
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return this.validator.test(stack);
    }
}
