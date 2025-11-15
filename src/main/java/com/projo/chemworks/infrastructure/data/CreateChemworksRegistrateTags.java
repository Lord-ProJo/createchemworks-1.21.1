package com.projo.chemworks.infrastructure.data;

import com.projo.chemworks.CWTags;
import com.projo.chemworks.CreateChemworks;
import com.simibubi.create.foundation.data.TagGen;
import com.simibubi.create.infrastructure.data.CreateRegistrateTags;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class CreateChemworksRegistrateTags {
    public static void addGenerators() {
        CreateChemworks.REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, CreateChemworksRegistrateTags::genBlockTags);
        CreateChemworks.REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CreateChemworksRegistrateTags::genItemTags);
        CreateChemworks.REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, CreateChemworksRegistrateTags::genFluidTags);
        CreateChemworks.REGISTRATE.addDataGenerator(ProviderType.ENTITY_TAGS, CreateChemworksRegistrateTags::genEntityTags);
    }

    private static void genBlockTags(RegistrateTagsProvider<Block> provIn) {
        TagGen.CreateTagsProvider<Block> prov = new TagGen.CreateTagsProvider<>(provIn, Block::builtInRegistryHolder);

        ;

        for (CWTags.CWBlockTags tag : CWTags.CWBlockTags.values()) {
            if (tag.alwaysDatagen) {
                prov.getOrCreateRawBuilder(tag.tag);
            }
        }
    }

    private static void genItemTags(RegistrateTagsProvider<Item> provIn) {
        TagGen.CreateTagsProvider<Item> prov = new TagGen.CreateTagsProvider<>(provIn, Item::builtInRegistryHolder);

        for (CWTags.CWItemTags tag : CWTags.CWItemTags.values()) {
            if (tag.alwaysDatagen) {
                prov.getOrCreateRawBuilder(tag.tag);
            }
        }
    }

    private static void genFluidTags(RegistrateTagsProvider<Fluid> provIn) {
        TagGen.CreateTagsProvider<Fluid> prov = new TagGen.CreateTagsProvider<>(provIn, Fluid::builtInRegistryHolder);


        for (CWTags.CWFluidTags tag : CWTags.CWFluidTags.values()) {
            if (tag.alwaysDatagen) {
                prov.getOrCreateRawBuilder(tag.tag);
            }
        }
    }

    private static void genEntityTags(RegistrateTagsProvider<EntityType<?>> provIn) {
        TagGen.CreateTagsProvider<EntityType<?>> prov = new TagGen.CreateTagsProvider<>(provIn, EntityType::builtInRegistryHolder);

        for (CWTags.CWEntityTags tag : CWTags.CWEntityTags.values()) {
            if (tag.alwaysDatagen) {
                prov.getOrCreateRawBuilder(tag.tag);
            }
        }
    }

}
