
package net.mcreator.sotmr.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.sotmr.itemgroup.SOTMRocksAndStonesItemGroup;
import net.mcreator.sotmr.SotmModElements;

import java.util.List;
import java.util.Collections;

@SotmModElements.ModElement.Tag
public class GreenMistystStairsBlock extends SotmModElements.ModElement {
	@ObjectHolder("sotm:green_mistyst_stairs")
	public static final Block block = null;
	public GreenMistystStairsBlock(SotmModElements instance) {
		super(instance, 534);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(SOTMRocksAndStonesItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends StairsBlock {
		public CustomBlock() {
			super(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 10f)).getDefaultState(),
					Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).harvestLevel(0)
							.harvestTool(ToolType.PICKAXE).setRequiresTool());
			setRegistryName("green_mistyst_stairs");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
