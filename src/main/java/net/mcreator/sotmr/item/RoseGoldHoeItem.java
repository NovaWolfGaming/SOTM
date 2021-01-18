
package net.mcreator.sotmr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.HoeItem;

import net.mcreator.sotmr.itemgroup.ToolsAndWeaponsItemGroup;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class RoseGoldHoeItem extends SotmModElements.ModElement {
	@ObjectHolder("sotm:rose_gold_hoe")
	public static final Item block = null;
	public RoseGoldHoeItem(SotmModElements instance) {
		super(instance, 1918);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 706;
			}

			public float getEfficiency() {
				return 9f;
			}

			public float getAttackDamage() {
				return 1f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 29;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(RoseGoldIngotItem.block, (int) (1)));
			}
		}, 0, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {
		}.setRegistryName("rose_gold_hoe"));
	}
}
