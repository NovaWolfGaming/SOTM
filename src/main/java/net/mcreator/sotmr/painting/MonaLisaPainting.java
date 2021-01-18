
package net.mcreator.sotmr.painting;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.entity.item.PaintingType;

import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class MonaLisaPainting extends SotmModElements.ModElement {
	public MonaLisaPainting(SotmModElements instance) {
		super(instance, 1347);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPaintingType(RegistryEvent.Register<PaintingType> event) {
		event.getRegistry().register(new PaintingType(32, 48).setRegistryName("mona_lisa"));
	}
}
