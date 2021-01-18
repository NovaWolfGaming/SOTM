
package net.mcreator.sotmr.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.sotmr.item.RubyGemItem;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SOTMItemsItemGroup extends SotmModElements.ModElement {
	public SOTMItemsItemGroup(SotmModElements instance) {
		super(instance, 686);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabsotmitems") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(RubyGemItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
