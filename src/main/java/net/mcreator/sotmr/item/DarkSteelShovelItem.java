
package net.mcreator.sotmr.item;

@SotmModElements.ModElement.Tag
public class DarkSteelShovelItem extends SotmModElements.ModElement {

	@ObjectHolder("sotm:dark_steel_shovel")
	public static final Item block = null;

	public DarkSteelShovelItem(SotmModElements instance) {
		super(instance, 2167);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ShovelItem(new IItemTier() {
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
				return Ingredient.fromStacks(new ItemStack(DarkSteelIngotItem.block, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {

		}.setRegistryName("dark_steel_shovel"));
	}

}
