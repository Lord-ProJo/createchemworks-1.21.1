package com.projo.chemworks.infrastructure.worldgen;

import com.projo.chemworks.CreateChemworks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class CWBiomeModifiers {
    public static final ResourceKey<BiomeModifier>
            ADD_SULFUR_DEPOSIT = key("add_sulfur_deposit")
            ;


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomeLookup = context.lookup(Registries.BIOME);
        HolderSet<Biome> isBasaltDeltas = HolderSet.direct(biomeLookup.getOrThrow(Biomes.BASALT_DELTAS));

        HolderGetter<PlacedFeature> featureLookup = context.lookup(Registries.PLACED_FEATURE);
        Holder<PlacedFeature> sulfurDeposit = featureLookup.getOrThrow(CWPlacedFeatures.SULFUR_DEPOSIT_PLACED);

        context.register(ADD_SULFUR_DEPOSIT, addOre(isBasaltDeltas, sulfurDeposit ));
    }

    private static BiomeModifiers.AddFeaturesBiomeModifier addOre(HolderSet<Biome> biomes, Holder<PlacedFeature> feature) {
        return new BiomeModifiers.AddFeaturesBiomeModifier(biomes, HolderSet.direct(feature), GenerationStep.Decoration.UNDERGROUND_ORES);
    }

    private static ResourceKey<BiomeModifier> key(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CreateChemworks.asResource(name));
    }
}
