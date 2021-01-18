package net.mcreator.sotmr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.mcreator.sotmr.block.NetherMudBlock;
import net.mcreator.sotmr.block.NetherGrassMudBlock;
import net.mcreator.sotmr.SotmModElements;
import net.mcreator.sotmr.SotmMod;

import java.util.Map;

@SotmModElements.ModElement.Tag
public class NetherGrassMudUpdateTickProcedure extends SotmModElements.ModElement {
	public NetherGrassMudUpdateTickProcedure(SotmModElements instance) {
		super(instance, 684);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SotmMod.LOGGER.warn("Failed to load dependency x for procedure NetherGrassMudUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SotmMod.LOGGER.warn("Failed to load dependency y for procedure NetherGrassMudUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SotmMod.LOGGER.warn("Failed to load dependency z for procedure NetherGrassMudUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SotmMod.LOGGER.warn("Failed to load dependency world for procedure NetherGrassMudUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))
				&& (((((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == NetherGrassMudBlock.block.getDefaultState()
						.getBlock())
						|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == NetherMudBlock.block.getDefaultState()
								.getBlock()))
						|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == NetherMudBlock.block.getDefaultState()
								.getBlock())
								|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == NetherGrassMudBlock.block
										.getDefaultState().getBlock())))
						|| (((((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getBlock() == NetherGrassMudBlock.block
								.getDefaultState().getBlock())
								|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getBlock() == NetherMudBlock.block
										.getDefaultState().getBlock()))
								|| (((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == NetherMudBlock.block
										.getDefaultState().getBlock())
										|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z)))
												.getBlock() == NetherMudBlock.block.getDefaultState().getBlock())))
								|| ((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1))))
										.getBlock() == NetherGrassMudBlock.block.getDefaultState().getBlock())
										|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1))))
												.getBlock() == NetherMudBlock.block.getDefaultState().getBlock()))
										|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1))))
												.getBlock() == NetherMudBlock.block.getDefaultState().getBlock())
												|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1))))
														.getBlock() == NetherMudBlock.block.getDefaultState().getBlock()))))))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), NetherGrassMudBlock.block.getDefaultState(), 3);
		}
	}
}
