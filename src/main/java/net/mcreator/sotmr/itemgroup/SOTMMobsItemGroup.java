
package net.mcreator.sotmr.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SOTMMobsItemGroup extends SotmModElements.ModElement {
	public SOTMMobsItemGroup(SotmModElements instance) {
		super(instance, 987);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabsotm_mobs") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(Items.EGG, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
