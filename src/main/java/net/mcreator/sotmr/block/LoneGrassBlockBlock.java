
package net.mcreator.sotmr.block;

import net.minecraft.block.material.Material;

@SotmModElements.ModElement.Tag
public class LoneGrassBlockBlock extends SotmModElements.ModElement {

	@ObjectHolder("sotm:lone_grass_block")
	public static final Block block = null;

	public LoneGrassBlockBlock(SotmModElements instance) {
		super(instance, 2197);

		FMLJavaModLoadingContext.get().getModEventBus().register(new BlockColorRegisterHandler());
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(SOTMBlocksItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	private static class BlockColorRegisterHandler {
		@OnlyIn(Dist.CLIENT)
		@SubscribeEvent
		public void blockColorLoad(ColorHandlerEvent.Block event) {
			event.getBlockColors().register((bs, world, pos, index) -> {
				return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D);
			}, block);
		}
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0)
							.harvestLevel(0).harvestTool(ToolType.SHOVEL));

			setRegistryName("lone_grass_block");
		}

		@OnlyIn(Dist.CLIENT)
		public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
			return adjacentBlockState.getBlock() == this ? true : super.isSideInvisible(state, adjacentBlockState, side);
		}

		@Override
		public MaterialColor getMaterialColor() {
			return MaterialColor.GRASS;
		}

		@Override
		public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction direction, IPlantable plantable) {
			return true;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(LoneDirtItem.block, (int) (1)));
		}

	}

}
