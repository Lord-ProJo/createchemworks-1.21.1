package com.projo.chemworks;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

@SuppressWarnings("unused")
public class CWDamageTypes {
    private static ResourceKey<DamageType> key(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, CreateChemworks.asResource(name));
    }

    public static void bootstrap(BootstrapContext<DamageType> ctx) {

    }
}
