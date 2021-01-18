
package net.mcreator.sotmr.painting;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.entity.item.PaintingType;

import net.mcreator.sotmr.SotmModElements;

@SotmModElements.ModElement.Tag
public class VanGoghPainting extends SotmModElements.ModElement {
	public VanGoghPainting(SotmModElements instance) {
		super(instance, 1345);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPaintingType(RegistryEvent.Register<PaintingType> event) {
		event.getRegistry().register(new PaintingType(16, 16).setRegistryName("van_gogh"));
	}
}
