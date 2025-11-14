package com.projo.chemworks.item;

import com.projo.chemworks.CreateChemworks;
import com.projo.chemworks.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateChemworks.MOD_ID);

    public static final Supplier<CreativeModeTab> CHEMWORKS_ITEMS_TAB = CREATIVE_MODE_TAB.register("chemworks_items_tab",
            () -> CreativeModeTab.builder()
                    .icon( () -> new ItemStack(ModItems.RAW_SULFUR.get()))
                    .title(Component.translatable("creativetab.createchemworks.sulfur.items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_SULFUR);
                        output.accept(ModItems.SULFUR_CRYSTAL);
                        //output.accept(ModBlocks.SULFUR_ORE);
                            }).build());

    public static final Supplier<CreativeModeTab> CHEMWORKS_BLOCK_TAB = CREATIVE_MODE_TAB.register("chemworks_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon( () -> new ItemStack(ModBlocks.RAW_SULFUR_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CreateChemworks.MOD_ID,"chemworks_items_tab"))
                    .title(Component.translatable("creativetab.createchemworks.sulfur.block"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.RAW_SULFUR_BLOCK);
                        output.accept(ModBlocks.SULFUR_ORE);

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }


}
