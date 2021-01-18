
package net.mcreator.sotmr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.sotmr.itemgroup.ToolsAndWeaponsItemGroup;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class Crystone_GoldSwordItem extends SotmModElements.ModElement {
	@ObjectHolder("sotm:crystone_gold_sword")
	public static final Item block = null;
	public Crystone_GoldSwordItem(SotmModElements instance) {
		super(instance, 2069);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 250;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 4f;
			}

			public int getHarvestLevel() {
				return 2;
			}

			public int getEnchantability() {
				return 14;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Crystone_GoldIngotItem.block, (int) (1)));
			}
		}, 3, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {
		}.setRegistryName("crystone_gold_sword"));
	}
}
