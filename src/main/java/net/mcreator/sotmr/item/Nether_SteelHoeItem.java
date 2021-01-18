
package net.mcreator.sotmr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.HoeItem;

import net.mcreator.sotmr.itemgroup.ToolsAndWeaponsItemGroup;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class Nether_SteelHoeItem extends SotmModElements.ModElement {
	@ObjectHolder("sotm:nether_steel_hoe")
	public static final Item block = null;
	public Nether_SteelHoeItem(SotmModElements instance) {
		super(instance, 443);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 361;
			}

			public float getEfficiency() {
				return 7f;
			}

			public float getAttackDamage() {
				return 1f;
			}

			public int getHarvestLevel() {
				return 3;
			}

			public int getEnchantability() {
				return 18;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}
		}, 0, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {
		}.setRegistryName("nether_steel_hoe"));
	}
}
