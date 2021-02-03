
package net.mcreator.sotmr.fuel;

@SotmModElements.ModElement.Tag
public class OilFuelFuel extends SotmModElements.ModElement {

	public OilFuelFuel(SotmModElements instance) {
		super(instance, 2187);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(OilBottleItem.block, (int) (1)).getItem())
			event.setBurnTime(1650);
	}

}
