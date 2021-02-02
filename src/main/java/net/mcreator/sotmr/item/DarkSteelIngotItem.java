
package net.mcreator.sotmr.item;

@SotmModElements.ModElement.Tag
public class DarkSteelIngotItem extends SotmModElements.ModElement {

	@ObjectHolder("sotm:dark_steel_ingot")
	public static final Item block = null;

	public DarkSteelIngotItem(SotmModElements instance) {
		super(instance, 2158);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(SOTMItemsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("dark_steel_ingot");
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
