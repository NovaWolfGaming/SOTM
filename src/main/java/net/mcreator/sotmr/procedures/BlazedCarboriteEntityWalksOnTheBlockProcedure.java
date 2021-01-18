package net.mcreator.sotmr.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.sotmr.SotmModElements;
import net.mcreator.sotmr.SotmMod;

import java.util.Map;

@SotmModElements.ModElement.Tag
public class BlazedCarboriteEntityWalksOnTheBlockProcedure extends SotmModElements.ModElement {
	public BlazedCarboriteEntityWalksOnTheBlockProcedure(SotmModElements instance) {
		super(instance, 1266);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SotmMod.LOGGER.warn("Failed to load dependency entity for procedure BlazedCarboriteEntityWalksOnTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.IN_FIRE, (float) 1);
	}
}
