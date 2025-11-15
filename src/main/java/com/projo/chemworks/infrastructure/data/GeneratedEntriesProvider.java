package com.projo.chemworks.infrastructure.data;

import com.projo.chemworks.CWDamageTypes;
import com.projo.chemworks.CreateChemworks;
import com.projo.chemworks.infrastructure.worldgen.CWBiomeModifiers;
import com.projo.chemworks.infrastructure.worldgen.CWConfiguredFeatures;
import com.projo.chemworks.infrastructure.worldgen.CWPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GeneratedEntriesProvider extends DatapackBuiltinEntriesProvider {
    @SuppressWarnings({ "rawtypes", "unchecked" })

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, CWDamageTypes::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, (RegistrySetBuilder.RegistryBootstrap) CWConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, CWPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CWBiomeModifiers::bootstrap)
            ;
    public GeneratedEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(CreateChemworks.MOD_ID));
    }

    @Override
    public String getName() {
        return "CreateChemworks Generated Registry Entries";
    }

}
