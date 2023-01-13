package net.zupancic.fulguratio.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.zupancic.fulguratio.items.ModItems;

public enum ModArmorMaterial implements ArmorMaterial{

    //should probably be very basic
    FULGURIUM("fulguratio:fulgurium", 10, new int[] {2, 5, 4, 1}, 4, 
        SoundEvents.ARMOR_EQUIP_CHAIN, Ingredient.of(ModItems.FULGURIUM.get().getDefaultInstance()), 0.1f, 0f);


    //index goes from feet to head
    private static final int[] durabilityRatios = new int[] {13, 15, 16, 11};
    private final int[] defense;
    private final int enchantability;
    private final int baseDurability;//based
    private final SoundEvent equipSound;
    private final Ingredient repairIngredient;
    private final String name;
    private final float toughness;
    private final float knockbackResistance;
    
    ModArmorMaterial(String name, int baseDurability, 
        int[] defense, int enchantability, SoundEvent equipSound, 
        Ingredient repairIngredient, float toughness, float knockbackResistance){
            this.name = name;
            this.baseDurability = baseDurability;
            this.defense = defense;
            this.enchantability = enchantability;
            this.equipSound = equipSound;
            this.repairIngredient = repairIngredient;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
    }
        
    //public static final SoundEvent ARMOR_EQUIP_TURTLE = SoundEvents.register("item.armor.equip_turtle");

    @Override
    public int getDurabilityForSlot(EquipmentSlot eSlot) {
        return baseDurability*durabilityRatios[eSlot.getIndex()];
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot eSlot) {
        return defense[eSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }

}