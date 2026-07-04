package com.kian.villagercapsule;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class VillagerCapsuleItem extends Item {

    public VillagerCapsuleItem(Properties props) {
        super(props);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (!stack.hasTag()) return ActionResult.pass(stack);

        CompoundNBT tag = stack.getTag();
        if (!tag.contains("villager")) return ActionResult.pass(stack);

        if (!world.isClientSide) {

            VillagerEntity villager = EntityType.VILLAGER.create(world);

            if (villager != null) {
                villager.load(tag.getCompound("villager"));
                villager.setPos(player.getX(), player.getY(), player.getZ());
                world.addFreshEntity(villager);
            }

            stack.shrink(1);
        }

        return ActionResult.sidedSuccess(stack, world.isClientSide());
    }
}