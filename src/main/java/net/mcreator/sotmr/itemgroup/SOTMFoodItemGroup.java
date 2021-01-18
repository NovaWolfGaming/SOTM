
package net.mcreator.sotmr.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.sotmr.item.CookedTropicalFishItem;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SOTMFoodItemGroup extends SotmModElements.ModElement {
	public SOTMFoodItemGroup(SotmModElements instance) {
		super(instance, 747);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabsotmfood") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CookedTropicalFishItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
