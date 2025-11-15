package com.projo.chemworks.infrastructure.worldgen;

import static net.minecraft.data.worldgen.features.FeatureUtils.register;

import com.projo.chemworks.CWBlocks;
import com.projo.chemworks.CreateChemworks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;


public class CWConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>>
            NETHER_SULFUR_DEPOSIT = key("sulfur_deposit")

            ;
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        //What bloc can  our block replace when generated
        RuleTest basaltReplaceables = new BlockMatchTest(Blocks.BASALT);

        // List of the different blocks that will replace the blocks specified in each rule.
        List<OreConfiguration.TargetBlockState> sulfurTargetStates = List.of(
                OreConfiguration.target(basaltReplaceables, CWBlocks.SULFUR_DEPOSIT.get().defaultBlockState()));

        register(context, NETHER_SULFUR_DEPOSIT,Feature.ORE, new OreConfiguration(sulfurTargetStates, 15));
    }



    private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, CreateChemworks.asResource(name));
    }


}
