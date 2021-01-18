
package net.mcreator.sotmr.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.sotmr.block.CopperOreBlockBlock;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SOTMBlocksItemGroup extends SotmModElements.ModElement {
	public SOTMBlocksItemGroup(SotmModElements instance) {
		super(instance, 669);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabsotmblocks") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CopperOreBlockBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
