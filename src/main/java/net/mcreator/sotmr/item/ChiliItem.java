
package net.mcreator.sotmr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import net.mcreator.sotmr.procedures.ChiliFoodEatenProcedure;
import net.mcreator.sotmr.itemgroup.SOTMFoodItemGroup;
import net.mcreator.sotmr.SotmModElements;

import java.util.Map;
import java.util.HashMap;

@SotmModElements.ModElement.Tag
public class ChiliItem extends SotmModElements.ModElement {
	@ObjectHolder("sotm:chili")
	public static final Item block = null;
	public ChiliItem(SotmModElements instance) {
		super(instance, 614);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(SOTMFoodItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(2).saturation(0.3f).build()));
			setRegistryName("chili");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemstack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemstack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				ChiliFoodEatenProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
