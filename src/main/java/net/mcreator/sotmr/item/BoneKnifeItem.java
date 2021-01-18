
package net.mcreator.sotmr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.sotmr.itemgroup.ToolsAndWeaponsItemGroup;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class BoneKnifeItem extends SotmModElements.ModElement {
	@ObjectHolder("sotm:bone_knife")
	public static final Item block = null;
	public BoneKnifeItem(SotmModElements instance) {
		super(instance, 1894);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 100;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 0f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(BoneShardItem.block, (int) (1)), new ItemStack(Items.BONE, (int) (1)));
			}
		}, 3, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {
		}.setRegistryName("bone_knife"));
	}
}
