package com.projo.chemworks;

import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.neoforged.neoforge.common.Tags;

import java.util.Map;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.simibubi.create.foundation.data.TagGen.tagBlockAndItem;

public class CWBlocks {

    static {
        CreateChemworks.REGISTRATE.setCreativeTab(CWCreativeModeTabs.MAIN);
    }

    public static final BlockEntry<Block> SULFUR_DEPOSIT =
            CreateChemworks.REGISTRATE.block("sulfur_deposit", Block::new)
            .initialProperties(() -> Blocks.NETHER_QUARTZ_ORE)
            .properties(p -> p.mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT))
            .transform(pickaxeOnly())
            .loot((lt, b) -> {
                HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = lt.getRegistries().lookupOrThrow(Registries.ENCHANTMENT);
                lt.add(b,
                        lt.createSilkTouchDispatchTable(b,
                                lt.applyExplosionDecay(b, LootItem.lootTableItem(CWItems.RAW_SULFUR.get())
                                        .apply(ApplyBonusCount.addOreBonusCount(enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE))))));
            })

            .tag(BlockTags.NEEDS_IRON_TOOL,
                    Tags.Blocks.ORES)
            .transform(tagBlockAndItem(Map.of()))
            .tag(Tags.Items.ORES)
            .model((c, p) -> {
                String path = "block/" + c.getName();
                p.cubeColumn(c.getName(), p.modLoc(path + "_side"), p.modLoc(path + "_end"));
            })
            .build()
            .register();

    public static final BlockEntry<Block> RAW_SULFUR_BLOCK =
            CreateChemworks.REGISTRATE.block("raw_sulfur_block", Block::new)
                    .initialProperties(SharedProperties::stone)
                    .transform(pickaxeOnly())
                    .tag(BlockTags.NEEDS_IRON_TOOL,
                            Tags.Blocks.STORAGE_BLOCKS)
                    .transform(tagBlockAndItem(Map.of()))
                    .tag(Tags.Items.STORAGE_BLOCKS)
                    .build()
                    .register();

// Load this class

    public static void register() {
        CreateChemworks.LOGGER.info("Registering ModBlocks for " + CreateChemworks.MOD_ID);
    }
}

