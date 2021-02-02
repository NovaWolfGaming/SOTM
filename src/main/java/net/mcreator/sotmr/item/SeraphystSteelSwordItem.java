
package net.mcreator.sotmr.item;

@SotmModElements.ModElement.Tag
public class SeraphystSteelSwordItem extends SotmModElements.ModElement {

	@ObjectHolder("sotm:seraphyst_steel_sword")
	public static final Item block = null;

	public SeraphystSteelSwordItem(SotmModElements instance) {
		super(instance, 2143);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 250;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 4f;
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
		}, 3, -3f, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)) {

		}.setRegistryName("seraphyst_steel_sword"));
	}

}
