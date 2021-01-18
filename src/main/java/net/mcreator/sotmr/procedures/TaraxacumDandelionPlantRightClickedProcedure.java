package net.mcreator.sotmr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.sotmr.block.TaraxacumDandelionBlownBlock;
import net.mcreator.sotmr.SotmModElements;
import net.mcreator.sotmr.SotmMod;

import java.util.Map;

@SotmModElements.ModElement.Tag
public class TaraxacumDandelionPlantRightClickedProcedure extends SotmModElements.ModElement {
	public TaraxacumDandelionPlantRightClickedProcedure(SotmModElements instance) {
		super(instance, 1313);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SotmMod.LOGGER.warn("Failed to load dependency entity for procedure TaraxacumDandelionPlantRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SotmMod.LOGGER.warn("Failed to load dependency x for procedure TaraxacumDandelionPlantRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SotmMod.LOGGER.warn("Failed to load dependency y for procedure TaraxacumDandelionPlantRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SotmMod.LOGGER.warn("Failed to load dependency z for procedure TaraxacumDandelionPlantRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SotmMod.LOGGER.warn("Failed to load dependency world for procedure TaraxacumDandelionPlantRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), TaraxacumDandelionBlownBlock.block.getDefaultState(), 3);
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LUCK, (int) 300, (int) 1));
	}
}
