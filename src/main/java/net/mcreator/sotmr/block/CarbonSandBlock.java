
package net.mcreator.sotmr.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.sotmr.particle.AshDustParticle;
import net.mcreator.sotmr.itemgroup.SOTMBlocksItemGroup;
import net.mcreator.sotmr.SotmModElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@SotmModElements.ModElement.Tag
public class CarbonSandBlock extends SotmModElements.ModElement {
	@ObjectHolder("sotm:carbonsand")
	public static final Block block = null;
	public CarbonSandBlock(SotmModElements instance) {
		super(instance, 108);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(SOTMBlocksItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends FallingBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).harvestLevel(0)
					.harvestTool(ToolType.SHOVEL).setRequiresTool());
			setRegistryName("carbonsand");
		}

		@Override
		public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction direction, IPlantable plantable) {
			return true;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
			super.animateTick(state, world, pos, random);
			PlayerEntity entity = Minecraft.getInstance().player;
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (true)
				for (int l = 0; l < 4; ++l) {
					double d0 = (x + random.nextFloat());
					double d1 = (y + random.nextFloat());
					double d2 = (z + random.nextFloat());
					int i1 = random.nextInt(2) * 2 - 1;
					double d3 = (random.nextFloat() - 0.5D) * 1D;
					double d4 = (random.nextFloat() - 0.5D) * 1D;
					double d5 = (random.nextFloat() - 0.5D) * 1D;
					world.addParticle(AshDustParticle.particle, d0, d1, d2, d3, d4, d5);
				}
		}
	}
}
