
package net.mcreator.sotmr.item;

@SotmModElements.ModElement.Tag
public class OilBottleItem extends SotmModElements.ModElement {

	@ObjectHolder("sotm:oil_bottle")
	public static final Item block = null;

	public OilBottleItem(SotmModElements instance) {
		super(instance, 2186);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(SOTMItemsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("oil_bottle");
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
