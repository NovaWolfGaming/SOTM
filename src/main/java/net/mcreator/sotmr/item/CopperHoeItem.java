
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
public class CopperHoeItem extends SotmModElements.ModElement {
	@ObjectHolder("sotm:copperhoe")
	public static final Item block = null;
	public CopperHoeItem(SotmModElements instance) {
		super(instance, 392);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 120;
			}

			public float getEfficiency() {
				return 18f;
			}

			public float getAttackDamage() {
				return -0.5f;
			}

			public int getHarvestLevel() {
				return 12;
			}

			public int getEnchantability() {
				return 84;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(CopperIngotItem.block, (int) (1)));
			}
		}, 0, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {
		}.setRegistryName("copperhoe"));
	}
}
