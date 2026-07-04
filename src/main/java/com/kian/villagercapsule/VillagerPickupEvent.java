package com.kian.villagercapsule;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VillagerPickupEvent {

    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.EntityInteract event) {

        PlayerEntity player = event.getPlayer();
        World world = event.getWorld();

        if (world.isClientSide) return;

        Entity target = event.getTarget();

        if (!(target instanceof VillagerEntity)) return;

        if (!player.isCrouching()) return;

        VillagerEntity villager = (VillagerEntity) target;

        ItemStack capsule = new ItemStack(ModItems.CAPSULE.get());

        CompoundNBT tag = new CompoundNBT();
        villager.save(tag);

        capsule.getOrCreateTag().put("villager", tag);

        if (player.inventory.add(capsule)) {
            villager.remove();
        }

        event.setCanceled(true);
    }
}