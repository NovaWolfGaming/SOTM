package net.mcreator.sotmr.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.sotmr.item.PebbleItem;
import net.mcreator.sotmr.SotmModElements;
import net.mcreator.sotmr.SotmMod;

import java.util.Map;
import java.util.HashMap;

@SotmModElements.ModElement.Tag
public class StoneDropProcedure extends SotmModElements.ModElement {
	public StoneDropProcedure(SotmModElements instance) {
		super(instance, 1125);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SotmMod.LOGGER.warn("Failed to load dependency entity for procedure StoneDrop!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SotmMod.LOGGER.warn("Failed to load dependency x for procedure StoneDrop!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SotmMod.LOGGER.warn("Failed to load dependency y for procedure StoneDrop!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SotmMod.LOGGER.warn("Failed to load dependency z for procedure StoneDrop!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SotmMod.LOGGER.warn("Failed to load dependency world for procedure StoneDrop!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Items.WOODEN_PICKAXE, (int) (1)).getItem())) {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock())) {
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(PebbleItem.block, (int) (1)));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(PebbleItem.block, (int) (1)));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.isRemote()) {
					ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(PebbleItem.block, (int) (1)));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event) {
		Entity entity = event.getPlayer();
		IWorld world = event.getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("xpAmount", event.getExpToDrop());
		dependencies.put("x", event.getPos().getX());
		dependencies.put("y", event.getPos().getY());
		dependencies.put("z", event.getPos().getZ());
		dependencies.put("px", entity.getPosX());
		dependencies.put("py", entity.getPosY());
		dependencies.put("pz", entity.getPosZ());
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
