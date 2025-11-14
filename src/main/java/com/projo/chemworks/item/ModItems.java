package com.projo.chemworks.item;

import com.projo.chemworks.CreateChemworks;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateChemworks.MOD_ID);

    public  static final DeferredItem<Item> IRON_POWDER = ITEMS.register("iron_powder",
            () -> new Item(new Item.Properties()));

    public  static final DeferredItem<Item> GOLD_POWDER = ITEMS.register("gold_powder",
            () -> new Item(new Item.Properties()));

    public  static final DeferredItem<Item> COPPER_POWDER = ITEMS.register("copper_powder",
            () -> new Item(new Item.Properties()));

    public  static final DeferredItem<Item> ZINC_POWDER = ITEMS.register("zinc_powder",
            () -> new Item(new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
