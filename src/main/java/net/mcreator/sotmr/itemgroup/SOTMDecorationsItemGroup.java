
package net.mcreator.sotmr.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.sotmr.block.PolishedBlackGraniteStairsBlock;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SOTMDecorationsItemGroup extends SotmModElements.ModElement {
	public SOTMDecorationsItemGroup(SotmModElements instance) {
		super(instance, 676);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabsotmdecorations") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(PolishedBlackGraniteStairsBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
