
package net.mcreator.sotmr.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.sotmr.block.EchorinBlockBlock;
import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class EchorinBlockFuelFuel extends SotmModElements.ModElement {
	public EchorinBlockFuelFuel(SotmModElements instance) {
		super(instance, 759);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(EchorinBlockBlock.block, (int) (1)).getItem())
			event.setBurnTime(4200);
	}
}
