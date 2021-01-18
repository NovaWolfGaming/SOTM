
package net.mcreator.sotmr.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.sotmr.item.RubySwordItem;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class ToolsAndWeaponsItemGroup extends SotmModElements.ModElement {
	public ToolsAndWeaponsItemGroup(SotmModElements instance) {
		super(instance, 666);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabtoolsandweapons") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(RubySwordItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
