package net.mcreator.sotmr.procedures;

@SotmModElements.ModElement.Tag
public class MilkBottleFoodEatenProcedure extends SotmModElements.ModElement {

	public MilkBottleFoodEatenProcedure(SotmModElements instance) {
		super(instance, 2182);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SotmMod.LOGGER.warn("Failed to load dependency entity for procedure MilkBottleFoodEaten!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof LivingEntity)
			((LivingEntity) entity).clearActivePotions();

	}

}
