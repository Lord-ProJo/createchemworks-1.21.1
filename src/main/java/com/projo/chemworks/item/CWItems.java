package com.projo.chemworks.item;

import com.projo.chemworks.CreateChemworks;
import com.simibubi.create.Create;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.TagKey;
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
