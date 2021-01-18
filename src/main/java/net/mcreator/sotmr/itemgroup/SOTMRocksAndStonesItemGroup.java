
package net.mcreator.sotmr.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.block.Blocks;

import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SOTMRocksAndStonesItemGroup extends SotmModElements.ModElement {
	public SOTMRocksAndStonesItemGroup(SotmModElements instance) {
		super(instance, 668);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabsotmrocksandstones") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(Blocks.STONE, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
