package com.kian.villagercapsule;

import net.minecraft.item.Item;
import net.minecraftforge.registries.*;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, VillagerCapsuleMod.MODID);

    public static final RegistryObject<Item> CAPSULE =
        ITEMS.register("villager_capsule",
            () -> new VillagerCapsuleItem(new Item.Properties().stacksTo(1)));
}