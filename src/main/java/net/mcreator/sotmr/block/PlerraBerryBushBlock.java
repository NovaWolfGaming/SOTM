
package net.mcreator.sotmr.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.feature.RandomPatchFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.World;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.RegistryKey;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.loot.LootContext;
import net.minecraft.item.TallBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.sotmr.itemgroup.SOTMDecorationsItemGroup;
import net.mcreator.sotmr.item.PlerraBerryItem;
import net.mcreator.sotmr.SotmModElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@SotmModElements.ModElement.Tag
public class PlerraBerryBushBlock extends SotmModElements.ModElement {
	@ObjectHolder("sotm:plerra_berry_bush")
	public static final Block block = null;
	public PlerraBerryBushBlock(SotmModElements instance) {
		super(instance, 2217);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustomFlower());
		elements.items.add(
				() -> new TallBlockItem(block, new Item.Properties().group(SOTMDecorationsItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		RandomPatchFeature feature = new RandomPatchFeature(BlockClusterFeatureConfig.field_236587_a_) {
			@Override
			public boolean generate(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, BlockClusterFeatureConfig config) {
				RegistryKey<World> dimensionType = world.getWorld().getDimensionKey();
				boolean dimensionCriteria = false;
				if (dimensionType == World.THE_END)
					dimensionCriteria = true;
				if (!dimensionCriteria)
					return false;
				return super.generate(world, generator, random, pos, config);
			}
		};
		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> (ConfiguredFeature<?, ?>) feature
				.withConfiguration(
						(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(block.getDefaultState()), new DoublePlantBlockPlacer()))
								.tries(64).func_227317_b_().build())
				.withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(14));
	}
	public static class BlockCustomFlower extends DoublePlantBlock {
		public BlockCustomFlower() {
			super(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.ROOT).hardnessAndResistance(0f, 0f)
					.setLightLevel(s -> 0));
			setRegistryName("plerra_berry_bush");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			if (state.get(BlockStateProperties.DOUBLE_BLOCK_HALF) != DoubleBlockHalf.LOWER)
				return Collections.emptyList();
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(PlerraBerryItem.block, (int) (2)));
		}

		@Override
		public PlantType getPlantType(IBlockReader world, BlockPos pos) {
			return PlantType.PLAINS;
		}
	}
}
