package net.mcreator.sotmr.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.sotmr.SotmModElements;
import net.mcreator.sotmr.SotmMod;

import java.util.Map;

@SotmModElements.ModElement.Tag
public class SpikedOnPotionActiveTickProcedure extends SotmModElements.ModElement {
	public SpikedOnPotionActiveTickProcedure(SotmModElements instance) {
		super(instance, 1405);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SotmMod.LOGGER.warn("Failed to load dependency entity for procedure SpikedOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.CACTUS, (float) 1);
	}
}
