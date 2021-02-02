
package net.mcreator.sotmr.item;

@SotmModElements.ModElement.Tag
public class SeraphystSteelShovelItem extends SotmModElements.ModElement {

	@ObjectHolder("sotm:seraphyst_steel_shovel")
	public static final Item block = null;

	public SeraphystSteelShovelItem(SotmModElements instance) {
		super(instance, 2144);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ShovelItem(new IItemTier() {
			public int getMaxUses() {
				return 250;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 0f;
			}

			public int getHarvestLevel() {
				return 2;
			}

			public int getEnchantability() {
				return 14;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(SeraphystSteelIngotItem.block, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {

		}.setRegistryName("seraphyst_steel_shovel"));
	}

}
