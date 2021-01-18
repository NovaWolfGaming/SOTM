
package net.mcreator.sotmr.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.sotmr.itemgroup.SOTMMobsItemGroup;
import net.mcreator.sotmr.SotmModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@SotmModElements.ModElement.Tag
public class ScorpionEntity extends SotmModElements.ModElement {
	public static EntityType entity = null;
	public ScorpionEntity(SotmModElements instance) {
		super(instance, 619);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("scorpion")
						.setRegistryName("scorpion");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -13421773, -3381760, new Item.Properties().group(SOTMMobsItemGroup.tab))
				.setRegistryName("scorpion_spawn_egg"));
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = false;
		if (new ResourceLocation("sotm:darkstonedeltas").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("sotm:charred_plains").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("sotm:carbondesert").equals(event.getName()))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;
		event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(entity, 5, 1, 1));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(this::setupAttributes);
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);
		DungeonHooks.addDungeonMob(entity, 180);
	}
	private static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
				return new MobRenderer(renderManager, new ModelScorpion(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("sotm:textures/scorpion.png");
					}
				};
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 10);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3);
		GlobalEntityTypeAttributes.put(entity, ammma.create());
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, true));
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(4, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new SwimGoal(this));
			this.goalSelector.addGoal(7, new LeapAtTargetGoal(this, (float) 0.5));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ARTHROPOD;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}
	}

	// Date: 16/10/2019 11:59:06
	// Template version 1.1
	// Java generated by Techne
	// Keep in mind that you still need to fill in some blanks
	// - ZeuX
	public static class ModelScorpion extends EntityModel<Entity> {
		// fields
		ModelRenderer Head;
		ModelRenderer Body;
		ModelRenderer RearEnd;
		ModelRenderer Leg8;
		ModelRenderer Leg6;
		ModelRenderer Leg4;
		ModelRenderer Leg2;
		ModelRenderer Leg7;
		ModelRenderer Leg5;
		ModelRenderer Leg3;
		ModelRenderer Leg1;
		ModelRenderer RearEnd1;
		ModelRenderer RearEnd2;
		ModelRenderer RearEnd3;
		ModelRenderer Claw1;
		ModelRenderer Claw2;
		ModelRenderer Claw3;
		ModelRenderer Claw4;
		public ModelScorpion() {
			textureWidth = 64;
			textureHeight = 32;
			Head = new ModelRenderer(this, 32, 4);
			Head.addBox(-4F, -4F, -8F, 8, 8, 8);
			Head.setRotationPoint(0F, 20F, -3F);
			Head.setTextureSize(64, 32);
			Head.mirror = true;
			setRotation(Head, 0F, 0F, 0F);
			Body = new ModelRenderer(this, 15, 20);
			Body.addBox(-3F, -3F, -3F, 6, 6, 6);
			Body.setRotationPoint(0F, 20F, 0F);
			Body.setTextureSize(64, 32);
			Body.mirror = true;
			setRotation(Body, 0F, 0F, 0F);
			RearEnd = new ModelRenderer(this, 0, 5);
			RearEnd.addBox(-5F, -4F, -6F, 3, 4, 9);
			RearEnd.setRotationPoint(3.6F, 22F, 9F);
			RearEnd.setTextureSize(64, 32);
			RearEnd.mirror = true;
			setRotation(RearEnd, 0F, 0F, 0F);
			Leg8 = new ModelRenderer(this, 18, 0);
			Leg8.addBox(-1F, -1F, -1F, 16, 2, 2);
			Leg8.setRotationPoint(4F, 20F, -1F);
			Leg8.setTextureSize(64, 32);
			Leg8.mirror = true;
			setRotation(Leg8, 0F, 0.5759587F, 0.1919862F);
			Leg6 = new ModelRenderer(this, 18, 0);
			Leg6.addBox(-1F, -1F, -1F, 16, 2, 2);
			Leg6.setRotationPoint(4F, 20F, 0F);
			Leg6.setTextureSize(64, 32);
			Leg6.mirror = true;
			setRotation(Leg6, 0F, 0.2792527F, 0.1919862F);
			Leg4 = new ModelRenderer(this, 18, 0);
			Leg4.addBox(-1F, -1F, -1F, 16, 2, 2);
			Leg4.setRotationPoint(4F, 20F, 1F);
			Leg4.setTextureSize(64, 32);
			Leg4.mirror = true;
			setRotation(Leg4, 0F, -0.2792527F, 0.1919862F);
			Leg2 = new ModelRenderer(this, 18, 0);
			Leg2.addBox(-1F, -1F, -1F, 16, 2, 2);
			Leg2.setRotationPoint(4F, 20F, 2F);
			Leg2.setTextureSize(64, 32);
			Leg2.mirror = true;
			setRotation(Leg2, 0F, -0.5759587F, 0.1919862F);
			Leg7 = new ModelRenderer(this, 18, 0);
			Leg7.addBox(-15F, -1F, -1F, 16, 2, 2);
			Leg7.setRotationPoint(-4F, 20F, -1F);
			Leg7.setTextureSize(64, 32);
			Leg7.mirror = true;
			setRotation(Leg7, 0F, -0.5759587F, -0.1919862F);
			Leg5 = new ModelRenderer(this, 18, 0);
			Leg5.addBox(-15F, -1F, -1F, 16, 2, 2);
			Leg5.setRotationPoint(-4F, 20F, 0F);
			Leg5.setTextureSize(64, 32);
			Leg5.mirror = true;
			setRotation(Leg5, 0F, -0.2792527F, -0.1919862F);
			Leg3 = new ModelRenderer(this, 18, 0);
			Leg3.addBox(-15F, -1F, -1F, 16, 2, 2);
			Leg3.setRotationPoint(-4F, 20F, 1F);
			Leg3.setTextureSize(64, 32);
			Leg3.mirror = true;
			setRotation(Leg3, 0F, 0.2792527F, -0.1919862F);
			Leg1 = new ModelRenderer(this, 18, 0);
			Leg1.addBox(-15F, -1F, -1F, 16, 2, 2);
			Leg1.setRotationPoint(-4F, 20F, 2F);
			Leg1.setTextureSize(64, 32);
			Leg1.mirror = true;
			setRotation(Leg1, 0F, 0.5759587F, -0.1919862F);
			RearEnd1 = new ModelRenderer(this, 0, 18);
			RearEnd1.addBox(0F, 0F, 0F, 3, 3, 4);
			RearEnd1.setRotationPoint(-1.4F, 18F, 11F);
			RearEnd1.setTextureSize(64, 32);
			RearEnd1.mirror = true;
			setRotation(RearEnd1, 0.2602503F, 0F, 0F);
			RearEnd2 = new ModelRenderer(this, 0, 18);
			RearEnd2.addBox(0F, 0F, 0F, 3, 3, 4);
			RearEnd2.setRotationPoint(-1.3F, 17F, 14F);
			RearEnd2.setTextureSize(64, 32);
			RearEnd2.mirror = true;
			setRotation(RearEnd2, 0.4833219F, 0F, 0F);
			RearEnd3 = new ModelRenderer(this, 0, 26);
			RearEnd3.addBox(0F, 0F, 0F, 3, 2, 4);
			RearEnd3.setRotationPoint(-1.3F, 16F, 17F);
			RearEnd3.setTextureSize(64, 32);
			RearEnd3.mirror = true;
			setRotation(RearEnd3, 0.5948578F, 0F, 0F);
			Claw1 = new ModelRenderer(this, 42, 21);
			Claw1.addBox(0F, 0F, 0F, 2, 2, 5);
			Claw1.setRotationPoint(4F, 20F, -15F);
			Claw1.setTextureSize(64, 32);
			Claw1.mirror = true;
			setRotation(Claw1, -0.1487144F, -0.4461433F, 0F);
			Claw2 = new ModelRenderer(this, 42, 21);
			Claw2.addBox(0F, 0F, 0F, 2, 2, 5);
			Claw2.setRotationPoint(-5.4F, 20F, -14F);
			Claw2.setTextureSize(64, 32);
			Claw2.mirror = true;
			setRotation(Claw2, -0.1487144F, 0.3717861F, 0F);
			Claw3 = new ModelRenderer(this, 42, 28);
			Claw3.addBox(0F, 0F, 0F, 2, 1, 3);
			Claw3.setRotationPoint(-6.4F, 19.6F, -16.53333F);
			Claw3.setTextureSize(64, 32);
			Claw3.mirror = true;
			setRotation(Claw3, -0.1487144F, 0.3717861F, 0F);
			Claw4 = new ModelRenderer(this, 42, 28);
			Claw4.addBox(0F, 0F, 0F, 2, 1, 3);
			Claw4.setRotationPoint(5.25F, 19.55F, -17.6F);
			Claw4.setTextureSize(64, 32);
			Claw4.mirror = true;
			setRotation(Claw4, -0.1487144F, -0.4461433F, 0F);
		}

		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			Head.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Body.render(ms, vb, i1, i2, f1, f2, f3, f4);
			RearEnd.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Leg8.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Leg6.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Leg4.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Leg2.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Leg7.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Leg5.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Leg3.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Leg1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			RearEnd1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			RearEnd2.render(ms, vb, i1, i2, f1, f2, f3, f4);
			RearEnd3.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Claw1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Claw2.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Claw3.render(ms, vb, i1, i2, f1, f2, f3, f4);
			Claw4.render(ms, vb, i1, i2, f1, f2, f3, f4);
		}

		private void setRotation(ModelRenderer model, float x, float y, float z) {
			model.rotateAngleX = x;
			model.rotateAngleY = y;
			model.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.Leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Leg3.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.Leg4.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Leg5.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Leg6.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Leg7.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Leg8.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Claw1.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Claw1.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.Claw2.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Claw2.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.Claw3.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Claw3.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.Claw4.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Claw4.rotateAngleX = f4 / (180F / (float) Math.PI);
		}
	}
}
