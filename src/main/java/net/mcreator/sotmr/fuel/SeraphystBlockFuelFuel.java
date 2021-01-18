
package net.mcreator.sotmr.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.sotmr.block.SeraphystBlockBlock;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SeraphystBlockFuelFuel extends SotmModElements.ModElement {
	public SeraphystBlockFuelFuel(SotmModElements instance) {
		super(instance, 1270);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(SeraphystBlockBlock.block, (int) (1)).getItem())
			event.setBurnTime(6000);
	}
}
