package net.mcreator.sotmr.procedures;

@SotmModElements.ModElement.Tag
public class MercuryMobplayerCollidesBlockProcedure extends SotmModElements.ModElement {

	public MercuryMobplayerCollidesBlockProcedure(SotmModElements instance) {
		super(instance, 2194);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SotmMod.LOGGER.warn("Failed to load dependency entity for procedure MercuryMobplayerCollidesBlock!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WITHER, (int) 600, (int) 2, (true), (false)));

	}

}
