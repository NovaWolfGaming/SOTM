package net.mcreator.sotmr.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.sotmr.potion.HeatwavePotion;
import net.mcreator.sotmr.SotmModElements;
import net.mcreator.sotmr.SotmMod;

import java.util.Map;

@SotmModElements.ModElement.Tag
public class SkeletalCreeperPlayerCollidesWithThisEntityProcedure extends SotmModElements.ModElement {
	public SkeletalCreeperPlayerCollidesWithThisEntityProcedure(SotmModElements instance) {
		super(instance, 1267);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SotmMod.LOGGER.warn("Failed to load dependency entity for procedure SkeletalCreeperPlayerCollidesWithThisEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity instanceof PlayerEntity)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(HeatwavePotion.potion, (int) 60, (int) 1, (false), (false)));
		}
	}
}
