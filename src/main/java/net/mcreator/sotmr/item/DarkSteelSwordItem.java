
package net.mcreator.sotmr.item;

@SotmModElements.ModElement.Tag
public class DarkSteelSwordItem extends SotmModElements.ModElement {

	@ObjectHolder("sotm:dark_steel_sword")
	public static final Item block = null;

	public DarkSteelSwordItem(SotmModElements instance) {
		super(instance, 2166);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 361;
			}

			public float getEfficiency() {
				return 7f;
			}

			public float getAttackDamage() {
				return 6f;
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
		}, 3, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {

		}.setRegistryName("dark_steel_sword"));
	}

}
