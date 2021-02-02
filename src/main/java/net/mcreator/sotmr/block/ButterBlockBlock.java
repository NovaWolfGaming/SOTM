
package net.mcreator.sotmr.block;

import net.minecraft.block.material.Material;

@SotmModElements.ModElement.Tag
public class ButterBlockBlock extends SotmModElements.ModElement {

	@ObjectHolder("sotm:butter_block")
	public static final Block block = null;

	public ButterBlockBlock(SotmModElements instance) {
		super(instance, 2127);

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

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.SNOW).sound(SoundType.HONEY).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0)
							.slipperiness(0.9f).notSolid().setOpaque((bs, br, bp) -> false));

			setRegistryName("butter_block");
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
