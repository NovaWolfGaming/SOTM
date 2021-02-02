
package net.mcreator.sotmr.world.biome;

import net.minecraft.block.material.Material;
import java.util.ArrayList;
import java.util.HashMap;

@SotmModElements.ModElement.Tag
public class BasaltDeltasBiome extends SotmModElements.ModElement {

	public static Biome biome;

	public BasaltDeltasBiome(SotmModElements instance) {
		super(instance, 831);
		FMLJavaModLoadingContext.get().getModEventBus().register(new BiomeRegisterHandler());
	}

	private static class BiomeRegisterHandler {

		@SubscribeEvent
		public void registerBiomes(RegistryEvent.Register<Biome> event) {
			if (biome == null) {
				BiomeAmbience effects = new BiomeAmbience.Builder().setFogColor(-16777216).setWaterColor(-16777216).setWaterFogColor(-16777216)
						.withSkyColor(-16777216).withFoliageColor(-16777216).withGrassColor(-16777216).build();

				BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder()
						.withSurfaceBuilder(SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(BasaltBlock.block.getDefaultState(),
								BasaltBlock.block.getDefaultState(), BasaltBlock.block.getDefaultState())));

				biomeGenerationSettings.withStructure(StructureFeatures.MINESHAFT);

				biomeGenerationSettings.withStructure(StructureFeatures.SHIPWRECK);

				biomeGenerationSettings.withStructure(StructureFeatures.OCEAN_RUIN_WARM);

				DefaultBiomeFeatures.withCavesAndCanyons(biomeGenerationSettings);
				DefaultBiomeFeatures.withMonsterRoom(biomeGenerationSettings);
				DefaultBiomeFeatures.withOverworldOres(biomeGenerationSettings);
				DefaultBiomeFeatures.withLavaAndWaterLakes(biomeGenerationSettings);
				DefaultBiomeFeatures.withFossils(biomeGenerationSettings);

				MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();
				mobSpawnInfo.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ScorpionEntity.entity, 2, 1, 1));

				biome = new Biome.Builder().precipitation(Biome.RainType.RAIN).category(Biome.Category.NONE).depth(0.1f).scale(0.2f).temperature(0.5f)
						.downfall(0.5f).setEffects(effects).withMobSpawnSettings(mobSpawnInfo.copy())
						.withGenerationSettings(biomeGenerationSettings.build()).build();

				event.getRegistry().register(biome.setRegistryName("sotm:darkstonedeltas"));
			}
		}

	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BiomeDictionary.addTypes(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, WorldGenRegistries.BIOME.getKey(biome)), BiomeDictionary.Type.SPOOKY);
	}

}
