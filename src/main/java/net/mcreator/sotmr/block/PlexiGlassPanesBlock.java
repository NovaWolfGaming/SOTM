
package net.mcreator.sotmr.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.sotmr.itemgroup.SOTMBlocksItemGroup;
import net.mcreator.sotmr.SotmModElements;

import java.util.List;
import java.util.Collections;

@SotmModElements.ModElement.Tag
public class PlexiGlassPanesBlock extends SotmModElements.ModElement {
	@ObjectHolder("sotm:plexi_glass_panes")
	public static final Block block = null;
	public PlexiGlassPanesBlock(SotmModElements instance) {
		super(instance, 1536);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(SOTMBlocksItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getTranslucent());
	}
	public static class CustomBlock extends PaneBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(6f, 30f).setLightLevel(s -> 0).notSolid()
					.setOpaque((bs, br, bp) -> false));
			setRegistryName("plexi_glass_panes");
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
