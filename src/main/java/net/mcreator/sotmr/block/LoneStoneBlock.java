
package net.mcreator.sotmr.block;

import net.minecraft.block.material.Material;

@SotmModElements.ModElement.Tag
public class LoneStoneBlock extends SotmModElements.ModElement {

	@ObjectHolder("sotm:lone_stone")
	public static final Block block = null;

	public LoneStoneBlock(SotmModElements instance) {
		super(instance, 2195);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(SOTMRocksAndStonesItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).harvestLevel(1)
							.harvestTool(ToolType.PICKAXE));

			setRegistryName("lone_stone");
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
