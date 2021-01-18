
package net.mcreator.sotmr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.sotmr.itemgroup.ToolsAndWeaponsItemGroup;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class Nether_SteelPickaxeItem extends SotmModElements.ModElement {
	@ObjectHolder("sotm:nether_steel_pickaxe")
	public static final Item block = null;
	public Nether_SteelPickaxeItem(SotmModElements instance) {
		super(instance, 439);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 361;
			}

			public float getEfficiency() {
				return 7f;
			}

			public float getAttackDamage() {
				return 2f;
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
		}, 1, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {
		}.setRegistryName("nether_steel_pickaxe"));
	}
}
