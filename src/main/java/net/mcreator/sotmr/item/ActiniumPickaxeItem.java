
package net.mcreator.sotmr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.sotmr.itemgroup.ToolsAndWeaponsItemGroup;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class ActiniumPickaxeItem extends SotmModElements.ModElement {
	@ObjectHolder("sotm:actiniumpickaxe")
	public static final Item block = null;
	public ActiniumPickaxeItem(SotmModElements instance) {
		super(instance, 425);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 2719;
			}

			public float getEfficiency() {
				return 17f;
			}

			public float getAttackDamage() {
				return 9f;
			}

			public int getHarvestLevel() {
				return 11;
			}

			public int getEnchantability() {
				return 77;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(ActiniumIngotItem.block, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {
		}.setRegistryName("actiniumpickaxe"));
	}
}
