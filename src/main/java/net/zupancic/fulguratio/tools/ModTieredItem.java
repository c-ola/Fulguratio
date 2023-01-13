package net.zupancic.fulguratio.tools;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.zupancic.fulguratio.items.ModItems;

public enum ModTieredItem implements Tier{
    FULGURIUM(300, 1.0f, 3f, 3, 4, Ingredient.of(ModItems.FULGURIUM.get().getDefaultInstance()));

    private final int uses;
    private final float speed;
    private final float attackDamage;
    private final int level;
    private final int enchantability;
    private final Ingredient repairIngredient;

    ModTieredItem(int uses, float speed, float attackDamage, int level, int enchantability, Ingredient repairIngredient){
        this.uses = uses;
        this.speed = speed;
        this.attackDamage = attackDamage;
        this.level = level;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }
    
}
