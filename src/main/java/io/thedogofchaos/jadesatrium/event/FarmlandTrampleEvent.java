package io.thedogofchaos.jadesatrium.event;

import io.thedogofchaos.jadesatrium.common.data.ModItems;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FarmlandTrampleEvent {
    @SubscribeEvent
    public static void farmlandTrampleEvent(BlockEvent.FarmlandTrampleEvent farmlandTrampleEvent) {
        Entity trampler = farmlandTrampleEvent.getEntity();
        if (trampler instanceof LivingEntity) {
            ItemStack footwear = ((LivingEntity) trampler).getItemBySlot(EquipmentSlot.FEET);
            if (footwear.is(ModItems.ANTI_TRAMPLE_BOOTS.get())) {
                // no trample if wearing anti-trample boots
                farmlandTrampleEvent.setCanceled(true);
            }
        }
    }
}
