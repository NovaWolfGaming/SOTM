
package net.mcreator.sotmr.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.sotmr.block.RubyOreBlock;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SOTMOresItemGroup extends SotmModElements.ModElement {
	public SOTMOresItemGroup(SotmModElements instance) {
		super(instance, 667);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabsotmores") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(RubyOreBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
