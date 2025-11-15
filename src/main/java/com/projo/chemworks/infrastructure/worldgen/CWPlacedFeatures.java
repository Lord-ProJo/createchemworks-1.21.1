package com.projo.chemworks.infrastructure.worldgen;

import static net.minecraft.data.worldgen.placement.PlacementUtils.register;

import com.projo.chemworks.CreateChemworks;
import com.simibubi.create.infrastructure.worldgen.ConfigPlacementFilter;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class CWPlacedFeatures {
    public static final ResourceKey<PlacedFeature>
            SULFUR_DEPOSIT_PLACED = key("sulfur_deposit_placed")

            ;

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> featureLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> sulfurdeposit = featureLookup.getOrThrow(CWConfiguredFeatures.NETHER_SULFUR_DEPOSIT);

        register(context, SULFUR_DEPOSIT_PLACED, sulfurdeposit, placement(CountPlacement.of(500),-64, 64));

    }

    private static List<PlacementModifier> placementOres(PlacementModifier frequency, int minHeight, int maxHeight) {
        return List.of(
                frequency,
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)),
                ConfigPlacementFilter.INSTANCE
        );
    }

    private static List<PlacementModifier> placement(PlacementModifier frequency, int minHeight, int maxHeight) {
        return List.of(
                frequency,
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)),
                ConfigPlacementFilter.INSTANCE
        );
    }

    private static ResourceKey<PlacedFeature> key(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, CreateChemworks.asResource(name));
    }



}
