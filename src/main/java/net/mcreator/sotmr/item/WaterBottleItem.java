
package net.mcreator.sotmr.item;

@SotmModElements.ModElement.Tag
public class WaterBottleItem extends SotmModElements.ModElement {

	@ObjectHolder("sotm:water_bottle")
	public static final Item block = null;

	public WaterBottleItem(SotmModElements instance) {
		super(instance, 2184);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {

		public FoodItemCustom() {
			super(new Item.Properties().group(SOTMFoodItemGroup.tab).maxStackSize(16).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(1).saturation(0.1f)

							.build()));
			setRegistryName("water_bottle");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.DRINK;
		}

		@Override
		public net.minecraft.util.SoundEvent getEatSound() {
			return net.minecraft.util.SoundEvents.ENTITY_GENERIC_DRINK;
		}

	}

}
