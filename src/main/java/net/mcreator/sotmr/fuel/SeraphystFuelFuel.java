
package net.mcreator.sotmr.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.sotmr.item.SeraphystItem;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SeraphystFuelFuel extends SotmModElements.ModElement {
	public SeraphystFuelFuel(SotmModElements instance) {
		super(instance, 1257);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(SeraphystItem.block, (int) (1)).getItem())
			event.setBurnTime(3200);
	}
}
