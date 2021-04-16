
package net.mcreator.sotmr.block;

import net.minecraft.block.material.Material;

@SotmModElements.ModElement.Tag
public class RustyIronWallBlock extends SotmModElements.ModElement {

	@ObjectHolder("sotm:rusty_iron_wall")
	public static final Block block = null;

	public RustyIronWallBlock(SotmModElements instance) {
		super(instance, 2331);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(SOTMDecorationsItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}

	public static class CustomBlock extends WallBlock {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(3f, 20f).setLightLevel(s -> 0).notSolid()
							.setOpaque((bs, br, bp) -> false));

			setRegistryName("rusty_iron_wall");
		}

		private static final VoxelShape CENTER_POLE_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
		private static final VoxelShape WALL_CONNECTION_NORTH_SIDE_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 9.0D);
		private static final VoxelShape WALL_CONNECTION_SOUTH_SIDE_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 16.0D);
		private static final VoxelShape WALL_CONNECTION_WEST_SIDE_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
		private static final VoxelShape WALL_CONNECTION_EAST_SIDE_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);

		private boolean shouldConnect(BlockState state, boolean checkattach, Direction face) {
			boolean flag = state.getBlock() instanceof WallBlock
					|| state.getBlock() instanceof FenceGateBlock && FenceGateBlock.isParallel(state, face);
			return !cannotAttach(state.getBlock()) && checkattach || flag;
		}

		@Override /* failed to load code for net.minecraft.block.WallBlock */
		@Override /* failed to load code for net.minecraft.block.WallBlock */
		/* failed to load code for net.minecraft.block.WallBlock */
		/* failed to load code for net.minecraft.block.WallBlock */
		/* failed to load code for net.minecraft.block.WallBlock */
		/* failed to load code for net.minecraft.block.WallBlock */
		/* failed to load code for net.minecraft.block.WallBlock */
		/* failed to load code for net.minecraft.block.WallBlock */

		private static boolean hasHeightForProperty(BlockState state, Property<WallHeight> heightProperty) {
			return state.get(heightProperty) != WallHeight.NONE;
		}

		private static boolean compareShapes(VoxelShape shape1, VoxelShape shape2) {
			return !VoxelShapes.compare(shape2, shape1, IBooleanFunction.ONLY_FIRST);
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
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
