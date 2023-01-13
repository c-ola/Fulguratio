package net.zupancic.fulguratio.block_entity;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.zupancic.fulguratio.Fulguratio;
import net.zupancic.fulguratio.items.ModItems;

public class EssenceCraftingTableBlockEntity extends BlockEntity implements Container, MenuProvider, RecipeHolder{

    private int craftingTime = 4*20;
    private int timer = 0;
    private boolean ready = true;
    private boolean isActive = false;
    private int fuel = 0;
	private int progress = 0;
	private final ItemStackHandler inventory = new ItemStackHandler(3);
	private final LazyOptional<IItemHandlerModifiable> optional = LazyOptional.of(() -> this.inventory);
	public static final Component TITLE = Component.translatable("container." + Fulguratio.MODID + ".essence_crafting_table");
	private static final int AMOUNT_INGREDIENT_1 = 1;
	private static final int AMOUNT_INGREDIENT_2 = 1;
	private static final int AMOUNT_CREATED = 1;

		// private final ItemStack activator;
	// private final ItemStack essence;

	
	private final ContainerData data = new ContainerData(){

		@Override
		public int get(int index) {
			if (index == 0){
				return EssenceCraftingTableBlockEntity.this.progress;
			}
			// return switch (index){
			// 	case 0 -> EssenceCraftingTableBlockEntity.this.progress;
			// 	case 1 -> EssenceCraftingTableBlockEntity.this.craftingTime;
			// 	default -> 0;	
			// };
			return 0;
		};

		@Override
		public void set(int index, int value){
			if (index == 0){
				EssenceCraftingTableBlockEntity.this.progress = value;
			}
			// switch (index){
			// 	case 0 -> EssenceCraftingTableBlockEntity.this.progress = value;
			// 	default -> 0;	
			// };
		}
		
		@Override
		public int getCount(){
			return 3;
		}
	};

    public EssenceCraftingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.ESSENCE_CRAFTING_TABLE.get(), pos, state);
    }

	public void tick(){
		if(level == null)
			return;

		Item activator = inventory.getStackInSlot(0).getItem();
		Item essence = inventory.getStackInSlot(1).getItem();

		//best way to do this is probably to just compare names
		
		//temporary recipe lol
		if(activator.getName(inventory.getStackInSlot(0)).equals(essence.getName(inventory.getStackInSlot(1)))){

		
			timer++;
			if(timer > craftingTime){
				timer = 0;
				inventory.extractItem(0, AMOUNT_INGREDIENT_1, false);
				inventory.extractItem(1, AMOUNT_INGREDIENT_2, false);

				ItemStack output = ModItems.FULGURIUM_AWAKENED.get().getDefaultInstance();
				output.setCount(AMOUNT_CREATED);

				inventory.insertItem(2, output, false);
			}
		}
	}

	public void getRecipeInformation(){
		
	}

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        EssenceCraftingTableBlockEntity blockEntity = (EssenceCraftingTableBlockEntity) be;

        if(!level.isClientSide){
            


            if(blockEntity.isActive){

                //cooldown
                blockEntity.timer++;
                if(blockEntity.timer > blockEntity.craftingTime){
                    blockEntity.timer = 0;
    
                    //do something here
                    blockEntity.ready = true;
                    blockEntity.isActive = false;
                }
            }
        }
        
    }

	public ContainerData getContainerData(){
		return this.data;
	}

	public ItemStackHandler getInventory(){
		return this.inventory;
	}

	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return cap == ForgeCapabilities.ITEM_HANDLER ? this.optional.cast() : super.getCapability(cap, side);
	}

	@Override
	public void invalidateCaps() {
		this.optional.invalidate();
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.progress = nbt.getInt("Progress");
		this.inventory.deserializeNBT(nbt.getCompound("Inventory"));
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putInt("Progress", this.progress);
		nbt.put("Inventory", this.inventory.serializeNBT());
	}

    public int getFuel(){
        return this.fuel;
    }
    
    public void toggle() {
        this.isActive = !this.isActive;
    }

    public void setReady(boolean bool){
        this.ready = bool;
    }

    public boolean isReady(){
        return this.ready;
    }

	@Override
	public void clearContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Nullable
	public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRecipeUsed(@Nullable Recipe<?> p_40134_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Nullable
	public Recipe<?> getRecipeUsed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContainerSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getItem(int p_18941_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeItem(int p_18942_, int p_18943_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_18951_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItem(int p_18944_, ItemStack p_18945_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean stillValid(Player p_18946_) {
		// TODO Auto-generated method stub
		return false;
	}

}
