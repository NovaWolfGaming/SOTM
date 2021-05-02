
package net.mcreator.sotmr.block;

import net.minecraft.block.material.Material;

@SotmModElements.ModElement.Tag
public class SuperSpaceMCBlock extends SotmModElements.ModElement {

	@ObjectHolder("sotm:super_space_mc")
	public static final Block block = null;

	public SuperSpaceMCBlock(SotmModElements instance) {
		super(instance, 2590);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(null)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.WATER).sound(SoundType.GROUND).hardnessAndResistance(2.15f, 15.5f).setLightLevel(s -> 7)
							.harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().setNeedsPostProcessing((bs, br, bp) -> true)
							.setEmmisiveRendering((bs, br, bp) -> true));

			setRegistryName("super_space_mc");
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
