
package net.mcreator.sotmr.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.feature.LakesFeature;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.World;
import net.minecraft.world.ISeedReader;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.RegistryKey;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.BucketItem;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.Block;

import net.mcreator.sotmr.itemgroup.SOTMItemsItemGroup;
import net.mcreator.sotmr.SotmModElements;

import java.util.Random;

@SotmModElements.ModElement.Tag
public class AgaltBlock extends SotmModElements.ModElement {
	@ObjectHolder("sotm:agalt")
	public static final FlowingFluidBlock block = null;
	@ObjectHolder("sotm:agalt_bucket")
	public static final Item bucket = null;
	public static FlowingFluid flowing = null;
	public static FlowingFluid still = null;
	private ForgeFlowingFluid.Properties fluidproperties = null;
	public AgaltBlock(SotmModElements instance) {
		super(instance, 363);
		FMLJavaModLoadingContext.get().getModEventBus().register(new FluidRegisterHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}
	private static class FluidRegisterHandler {
		@SubscribeEvent
		public void registerFluids(RegistryEvent.Register<Fluid> event) {
			event.getRegistry().register(still);
			event.getRegistry().register(flowing);
		}
	}
	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(still, RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(flowing, RenderType.getTranslucent());
	}

	@Override
	public void initElements() {
		fluidproperties = new ForgeFlowingFluid.Properties(() -> still, () -> flowing,
				FluidAttributes.builder(new ResourceLocation("sotm:blocks/agalt"), new ResourceLocation("sotm:blocks/agalt")).luminosity(3)
						.density(990).viscosity(995)).bucket(() -> bucket).block(() -> block);
		still = (FlowingFluid) new ForgeFlowingFluid.Source(fluidproperties).setRegistryName("agalt");
		flowing = (FlowingFluid) new ForgeFlowingFluid.Flowing(fluidproperties).setRegistryName("agalt_flowing");
		elements.blocks.add(() -> new FlowingFluidBlock(still, Block.Properties.create(Material.LAVA)) {
		}.setRegistryName("agalt"));
		elements.items
				.add(() -> new BucketItem(still, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(SOTMItemsItemGroup.tab))
						.setRegistryName("agalt_bucket"));
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS)
				.add(() -> new LakesFeature(BlockStateFeatureConfig.field_236455_a_) {
					@Override
					public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
						RegistryKey<World> dimensionType = world.getWorld().getDimensionKey();
						boolean dimensionCriteria = false;
						if (dimensionType == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("sotm:underworld")))
							dimensionCriteria = true;
						if (!dimensionCriteria)
							return false;
						return super.generate(world, generator, rand, pos, config);
					}
				}.withConfiguration(new BlockStateFeatureConfig(block.getDefaultState()))
						.withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(5))));
	}
}
