
package net.mcreator.sotmr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.sotmr.itemgroup.SOTMItemsItemGroup;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class PigLeatherItem extends SotmModElements.ModElement {
	@ObjectHolder("sotm:pig_leather")
	public static final Item block = null;
	public PigLeatherItem(SotmModElements instance) {
		super(instance, 1349);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(SOTMItemsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("pig_leather");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
