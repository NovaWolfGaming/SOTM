
package net.mcreator.sotmr.painting;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.entity.item.PaintingType;

import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class SonOfManPainting extends SotmModElements.ModElement {
	public SonOfManPainting(SotmModElements instance) {
		super(instance, 1346);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPaintingType(RegistryEvent.Register<PaintingType> event) {
		event.getRegistry().register(new PaintingType(16, 16).setRegistryName("son_of_man"));
	}
}
