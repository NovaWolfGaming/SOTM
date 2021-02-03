
package net.mcreator.sotmr.block;

import net.minecraft.block.material.Material;

@SotmModElements.ModElement.Tag
public class MonochromeWoodLeavesBlock extends SotmModElements.ModElement {

	@ObjectHolder("sotm:monochrome_wood_leaves")
	public static final Block block = null;

	public MonochromeWoodLeavesBlock(SotmModElements instance) {
		super(instance, 2201);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ToolsAndWeaponsItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends LeavesBlock {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.LEAVES).sound(SoundType.PLANT).hardnessAndResistance(0.2f, 0.2f).setLightLevel(s -> 0)
							.notSolid());

			setRegistryName("monochrome_wood_leaves");
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 30;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

	}

}
