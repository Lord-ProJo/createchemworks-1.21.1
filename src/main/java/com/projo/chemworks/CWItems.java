package com.projo.chemworks;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings({"unused", "deprecation"})
public class CWItems {

    static {
        CreateChemworks.REGISTRATE.setCreativeTab(CWCreativeModeTabs.MAIN);
    }

    public static final ItemEntry<Item>
            RAW_SULFUR = CreateChemworks.REGISTRATE
            .item("raw_sulfur", Item::new)
            .tag(Tags.Items.RAW_MATERIALS)
            .register(),

            SULFUR_CRYSTAL = CreateChemworks.REGISTRATE
                            .item("sulfur_crystal",Item::new)
                            .tag(Tags.Items.INGOTS)
                            .register()
            ;

    // Load this class

    public static void register() {
    }

}
