
package net.mcreator.sotmr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.sotmr.itemgroup.SOTMFoodItemGroup;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SpiderlegItem extends SotmModElements.ModElement {
	@ObjectHolder("sotm:spiderleg")
	public static final Item block = null;
	public SpiderlegItem(SotmModElements instance) {
		super(instance, 1358);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(SOTMFoodItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(1).saturation(0.3f).meat().build()));
			setRegistryName("spiderleg");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}
	}
}
