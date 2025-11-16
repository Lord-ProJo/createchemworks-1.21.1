package com.projo.chemworks;

import com.projo.chemworks.infrastructure.data.CreateChemworksDatagen;
import com.simibubi.create.CreateBuildInfo;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

@Mod(CreateChemworks.MOD_ID)
public class CreateChemworks {
    public static final String MOD_ID = "createchemworks";
    public static final Logger LOGGER = LogUtils.getLogger();


    /*
     * <b>Other mods should not use this field!</b> If you are an addon developer, create your own instance of
     * {@link CreateRegistrate}.
     * </br
     * If you were using this instance to render a callback listener use {@link CreateRegistrateRegistrationCallback#register} instead.
     */
public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID)
        .defaultCreativeTab((ResourceKey<CreativeModeTab>) null)
        .setTooltipModifierFactory(item ->
                        new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                                .andThen(TooltipModifier.mapNull(KineticStats.create(item)))
        );


    // Constructor
    public CreateChemworks(IEventBus eventBus, ModContainer modContainer) {
        onCtor(eventBus, modContainer);
    }

    public static void onCtor(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("{} {} initializing! Commit hash: {}", MOD_ID, CreateBuildInfo.VERSION, CreateBuildInfo.GIT_COMMIT);

        ModLoadingContext modLoadingContext = ModLoadingContext.get();

        IEventBus neoforgeEventBus = NeoForge.EVENT_BUS;

        REGISTRATE.registerEventListeners(modEventBus);

        CWCreativeModeTabs.register(modEventBus);
        CWTags.init();
        CWBlocks.register();
        CWItems.register();


        modEventBus.addListener(CreateChemworks::init);
        modEventBus.addListener(CreateChemworks::onRegister);
        modEventBus.addListener(EventPriority.HIGHEST, CreateChemworksDatagen::gatherDataHighPriority);
        modEventBus.addListener(EventPriority.LOWEST, CreateChemworksDatagen::gatherData);
        //neoforgeEventBus.addListener(CWFluids::handleFluidEffect);



    }

    public static void init(final FMLCommonSetupEvent event) {
        //CWFluids.registerFluidInteractions();
    }

    public static void onRegister(final RegisterEvent event) {
        //CWFanProcessingTypes.register();

        if (event.getRegistry() == BuiltInRegistries.TRIGGER_TYPES) {
            //CWAdvancement.register();
            //CWTriggers.register();
        }
    }


    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
