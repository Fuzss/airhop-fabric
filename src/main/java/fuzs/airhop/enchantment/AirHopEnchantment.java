package fuzs.airhop.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.item.enchantment.WaterWalkerEnchantment;

/**
 * values based on frost walker enchantment
 */
public class AirHopEnchantment extends Enchantment {
    public AirHopEnchantment(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.ARMOR_FEET, slots);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 15;
    }

    @Override
    public int getMaxLevel() {
        // can't make this configurable as creative mod search is cached before config settings are loaded
        return 3;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    protected boolean checkCompatibility(Enchantment ench) {
        if (ench instanceof WaterWalkerEnchantment || ench instanceof FrostWalkerEnchantment) {
            return false;
        }
        return super.checkCompatibility(ench);
    }
}
