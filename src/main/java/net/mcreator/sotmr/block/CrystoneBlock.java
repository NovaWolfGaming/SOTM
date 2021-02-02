
package net.mcreator.sotmr.block;

import net.minecraft.block.material.Material;

@SotmModElements.ModElement.Tag
public class CrystoneBlock extends SotmModElements.ModElement {

	@ObjectHolder("sotm:crystone")
	public static final Block block = null;

	public CrystoneBlock(SotmModElements instance) {
		super(instance, 2130);

		MinecraftForge.EVENT_BUS.register(this);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(SOTMRocksAndStonesItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f, 10f).setLightLevel(s -> 11)
							.setNeedsPostProcessing((bs, br, bp) -> true).setEmmisiveRendering((bs, br, bp) -> true));

			setRegistryName("crystone");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(CrystoneNuggetItem.block, (int) (4)));
		}

	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> new OreFeature(OreFeatureConfig.CODEC) {
			@Override
			public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
				RegistryKey<World> dimensionType = world.getWorld().getDimensionKey();
				boolean dimensionCriteria = false;

				if (dimensionType == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("sotm:underworld")))
					dimensionCriteria = true;

				if (!dimensionCriteria)
					return false;

				return super.generate(world, generator, rand, pos, config);
			}
		}.withConfiguration(new OreFeatureConfig(new BlockMatchRuleTest(CarboriteBlock.block.getDefaultState().getBlock()) {
			public boolean test(BlockState blockAt, Random random) {
				boolean blockCriteria = false;
				if (blockAt.getBlock() == CarboriteBlock.block.getDefaultState().getBlock())
					blockCriteria = true;
				return blockCriteria;
			}

			protected IRuleTestType<?> getType() {
				return IRuleTestType.BLOCK_MATCH;
			}
		}, block.getDefaultState(), 16)).range(64).square().func_242731_b(10));
	}

}
