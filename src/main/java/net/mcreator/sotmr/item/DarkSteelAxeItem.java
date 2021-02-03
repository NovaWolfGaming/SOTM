
package net.mcreator.sotmr.item;

@SotmModElements.ModElement.Tag
public class DarkSteelAxeItem extends SotmModElements.ModElement {

	@ObjectHolder("sotm:dark_steel_axe")
	public static final Item block = null;

	public DarkSteelAxeItem(SotmModElements instance) {
		super(instance, 2165);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 361;
			}

			public float getEfficiency() {
				return 7f;
			}

			public float getAttackDamage() {
				return 10f;
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

		}.setRegistryName("dark_steel_axe"));
	}

}
