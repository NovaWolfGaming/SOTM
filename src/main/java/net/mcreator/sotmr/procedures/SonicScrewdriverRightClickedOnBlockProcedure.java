package net.mcreator.sotmr.procedures;

import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.Property;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.BoneMealItem;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.BlockState;

import net.mcreator.sotmr.item.ArtronBallItem;
import net.mcreator.sotmr.block.CarboriteBlock;
import net.mcreator.sotmr.block.ArtronOreBlock;
import net.mcreator.sotmr.SotmModElements;
import net.mcreator.sotmr.SotmMod;

import java.util.Random;
import java.util.Map;

@SotmModElements.ModElement.Tag
public class SonicScrewdriverRightClickedOnBlockProcedure extends SotmModElements.ModElement {
	public SonicScrewdriverRightClickedOnBlockProcedure(SotmModElements instance) {
		super(instance, 1597);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				SotmMod.LOGGER.warn("Failed to load dependency itemstack for procedure SonicScrewdriverRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SotmMod.LOGGER.warn("Failed to load dependency x for procedure SonicScrewdriverRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SotmMod.LOGGER.warn("Failed to load dependency y for procedure SonicScrewdriverRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SotmMod.LOGGER.warn("Failed to load dependency z for procedure SonicScrewdriverRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SotmMod.LOGGER.warn("Failed to load dependency world for procedure SonicScrewdriverRightClickedOnBlock!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof World)
			((World) world).notifyNeighborsOfStateChange(new BlockPos((int) x, (int) y, (int) z),
					((World) world).getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock());
		{
			TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
			int _amount = (int) 100;
			if (_ent != null)
				_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
		}
		if (world instanceof World) {
			if (BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), (World) world, new BlockPos((int) x, (int) y, (int) z)) || BoneMealItem
					.growSeagrass(new ItemStack(Items.BONE_MEAL), (World) world, new BlockPos((int) x, (int) y, (int) z), (Direction) null)) {
				if (!world.isRemote())
					((World) world).playEvent(2005, new BlockPos((int) x, (int) y, (int) z), 0);
			}
		}
		{
			ItemStack _ist = (itemstack);
			if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
				_ist.shrink(1);
				_ist.setDamage(0);
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ArtronOreBlock.block.getDefaultState().getBlock())) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(ArtronBallItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
			{
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				BlockState _bs = CarboriteBlock.block.getDefaultState();
				BlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
					Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
					if (_property != null && _bs.get(_property) != null)
						try {
							_bs = _bs.with(_property, (Comparable) entry.getValue());
						} catch (Exception e) {
						}
				}
				world.setBlockState(_bp, _bs, 3);
			}
		}
	}
}
