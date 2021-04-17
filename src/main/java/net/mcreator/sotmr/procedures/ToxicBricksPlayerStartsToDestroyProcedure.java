package net.mcreator.sotmr.procedures;

@SotmModElements.ModElement.Tag
public class ToxicBricksPlayerStartsToDestroyProcedure extends SotmModElements.ModElement {

	public ToxicBricksPlayerStartsToDestroyProcedure(SotmModElements instance) {
		super(instance, 2336);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SotmMod.LOGGER.warn("Failed to load dependency entity for procedure ToxicBricksPlayerStartsToDestroy!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WITHER, (int) 200, (int) 1, (true), (false)));

	}

}
