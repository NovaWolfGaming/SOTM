package net.mcreator.sotmr.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.sotmr.SotmModElements;
import net.mcreator.sotmr.SotmMod;

import java.util.Map;

@SotmModElements.ModElement.Tag
public class HeatwaveOnPotionActiveTickProcedure extends SotmModElements.ModElement {
	public HeatwaveOnPotionActiveTickProcedure(SotmModElements instance) {
		super(instance, 1083);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SotmMod.LOGGER.warn("Failed to load dependency entity for procedure HeatwaveOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setFire((int) 5);
	}
}
