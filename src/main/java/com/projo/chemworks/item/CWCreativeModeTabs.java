package com.projo.chemworks.item;

import com.projo.chemworks.CreateChemworks;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.TagDependentIngredientItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import it.unimi.dsi.fastutil.objects.*;
import net.createmod.catnip.platform.CatnipServices;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class CWCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateChemworks.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = REGISTER.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.createchemworks.main"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(CWItems.RAW_SULFUR::asStack)
                    .displayItems(new RegistrateDisplayItemsGenerator(true, CWCreativeModeTabs.MAIN))
                    .build());

    private static class RegistrateDisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {
        private static final Predicate<Item> IS_ITEM_3D_PREDICATE;

        //Detect if an item have a 3D model
        static {
            MutableObject<Predicate<Item>> isItem3d = new MutableObject<>(item -> false);
            if (CatnipServices.PLATFORM.getEnv().isClient())
                isItem3d.setValue(makeClient3dItemPredicate());
            IS_ITEM_3D_PREDICATE = isItem3d.getValue();
        }

        @OnlyIn(Dist.CLIENT)
        private static Predicate<Item> makeClient3dItemPredicate() {
            return item -> {
                ItemRenderer itemRenderer = Minecraft.getInstance()
                        .getItemRenderer();
                BakedModel model = itemRenderer.getModel(new ItemStack(item), null, null, 0);
                return model.isGui3d();
            };
        }

        private final boolean addItems;
        private final DeferredHolder<CreativeModeTab, CreativeModeTab> tabFilter;

        public RegistrateDisplayItemsGenerator(boolean addItems, DeferredHolder<CreativeModeTab, CreativeModeTab> tabFilter) {
            this.addItems = addItems;
            this.tabFilter = tabFilter;
        }

        // Predict whether an item should be excluded
        private static Predicate<Item> makeExclusionPredicate() {
            Set<Item> exclusions = new ReferenceOpenHashSet<>();

            List<ItemProviderEntry<?, ?>> simpleExclusions = List.of(
                    // List of items excluded from the creative tab
            );

            List<ItemEntry<TagDependentIngredientItem>> tagDependentExclusions = List.of(
                    // Exclude all items requiring these ingredients. (generally items for integration with other optional mods)
            );

            // Exclude all items in the exclusion list
            for (ItemProviderEntry<?, ?> entry : simpleExclusions) {
                exclusions.add(entry.asItem());
            }
            // Exclude all items that contain an ingredient from the dependency list.
            for (ItemEntry<TagDependentIngredientItem> entry : tagDependentExclusions) {
                TagDependentIngredientItem item = entry.get();
                if (item.shouldHide()) {
                    exclusions.add(entry.asItem());
                }
            }
            return exclusions::contains;
        }

        private static List<ItemOrdering> makeOrderings() {
            List<ItemOrdering> orderings = new ReferenceArrayList<>();

            //Place an item before another Syntax : CWitem, CWItem.anotheritem
            Map<ItemProviderEntry<?, ?>, ItemProviderEntry<?, ?>> simpleBeforeOrderings = Map.of(
            );

            //Place an item after another Syntax : CWitem, CWItem.anotheritem
            Map<ItemProviderEntry<?, ?>, ItemProviderEntry<?, ?>> simpleAfterOrderings = Map.of(
            );

            // Place the first item before the second
            simpleBeforeOrderings.forEach((entry, otherEntry) -> {
                orderings.add(ItemOrdering.before(entry.asItem(), otherEntry.asItem()));
            });

            // Place the first item after the second
            simpleAfterOrderings.forEach((entry, otherEntry) -> {
                orderings.add(ItemOrdering.after(entry.asItem(), otherEntry.asItem()));
            });

            return orderings;
        }

        private static Function<Item, ItemStack> makeStackFunc() {
            Map<Item, Function<Item, ItemStack>> factories = new Reference2ReferenceOpenHashMap<>();

            //Allows you to automatically modify certain displayed ItemStacks. (Example: battery charged instead of empty)
            Map<ItemProviderEntry<?, ?>, Function<Item, ItemStack>> simpleFactories = Map.of(
            );

            simpleFactories.forEach((entry, factory) -> {
                factories.put(entry.asItem(), factory);
            });

            return item -> {
                Function<Item, ItemStack> factory = factories.get(item);
                if (factory != null) {
                    return factory.apply(item);
                }
                return new ItemStack(item);
            };
        }

        private static Function<Item, CreativeModeTab.TabVisibility> makeVisibilityFunc() {
            Map<Item, CreativeModeTab.TabVisibility> visibilities = new Reference2ObjectOpenHashMap<>();

            //List of items for which visibility needs to be specified in the creative tab
            Map<ItemProviderEntry<?, ?>, CreativeModeTab.TabVisibility> simpleVisibilities = Map.of(
                    //EXAMPLE : AllItems.BLAZE_CAKE_BASE, TabVisibility.SEARCH_TAB_ONLY
            );

            simpleVisibilities.forEach((entry, factory) -> {
                visibilities.put(entry.asItem(), factory);
            });
            //Example : remove visibility of all dyed seat except the red one
            /*for (BlockEntry<SeatBlock> entry : AllBlocks.SEATS) {
                SeatBlock block = entry.get();
                if (block.getColor() != DyeColor.RED) {
                    visibilities.put(entry.asItem(), TabVisibility.SEARCH_TAB_ONLY);
                }}*/

            return item -> {
                CreativeModeTab.TabVisibility visibility = visibilities.get(item);
                if (visibility != null) {
                    return visibility;
                }
                return CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;
            };
        }

        @Override
        public void accept(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
            Predicate<Item> exclusionPredicate = makeExclusionPredicate();
            List<ItemOrdering> orderings = makeOrderings();
            Function<Item, ItemStack> stackFunc = makeStackFunc();
            Function<Item, CreativeModeTab.TabVisibility> visibilityFunc = makeVisibilityFunc();

            List<Item> items = new LinkedList<>();
            if (addItems) {
                items.addAll(collectItems(exclusionPredicate.or(IS_ITEM_3D_PREDICATE.negate())));
            }
            items.addAll(collectBlocks(exclusionPredicate));
            if (addItems) {
                items.addAll(collectItems(exclusionPredicate.or(IS_ITEM_3D_PREDICATE)));
            }

            applyOrderings(items, orderings);
            outputAll(output, items, stackFunc, visibilityFunc);
        }

        private List<Item> collectBlocks(Predicate<Item> exclusionPredicate) {
            List<Item> items = new ReferenceArrayList<>();
            for (RegistryEntry<Block, Block> entry : CreateChemworks.REGISTRATE.getAll(Registries.BLOCK)) {
                if (!CreateRegistrate.isInCreativeTab(entry, tabFilter))
                    continue;
                Item item = entry.get()
                        .asItem();
                if (item == Items.AIR)
                    continue;
                if (!exclusionPredicate.test(item))
                    items.add(item);
            }
            items = new ReferenceArrayList<>(new ReferenceLinkedOpenHashSet<>(items));
            return items;
        }

        private List<Item> collectItems(Predicate<Item> exclusionPredicate) {
            List<Item> items = new ReferenceArrayList<>();
            for (RegistryEntry<Item, Item> entry : CreateChemworks.REGISTRATE.getAll(Registries.ITEM)) {
                if (!CreateRegistrate.isInCreativeTab(entry, tabFilter))
                    continue;
                Item item = entry.get();
                if (item instanceof BlockItem)
                    continue;
                if (!exclusionPredicate.test(item))
                    items.add(item);
            }
            return items;
        }

        private static void applyOrderings(List<Item> items, List<ItemOrdering> orderings) {
            for (ItemOrdering ordering : orderings) {
                int anchorIndex = items.indexOf(ordering.anchor());
                if (anchorIndex != -1) {
                    Item item = ordering.item();
                    int itemIndex = items.indexOf(item);
                    if (itemIndex != -1) {
                        items.remove(itemIndex);
                        if (itemIndex < anchorIndex) {
                            anchorIndex--;
                        }
                    }
                    if (ordering.type() == ItemOrdering.Type.AFTER) {
                        items.add(anchorIndex + 1, item);
                    } else {
                        items.add(anchorIndex, item);
                    }
                }
            }
        }

        private static void outputAll(CreativeModeTab.Output output, List<Item> items, Function<Item, ItemStack> stackFunc, Function<Item, CreativeModeTab.TabVisibility> visibilityFunc) {
            for (Item item : items) {
                output.accept(stackFunc.apply(item), visibilityFunc.apply(item));
            }
        }

        private record ItemOrdering(Item item, Item anchor, Type type) {
            public static ItemOrdering before(Item item, Item anchor) {
                return new ItemOrdering(item, anchor, Type.BEFORE);
            }

            public static ItemOrdering after(Item item, Item anchor) {
                return new ItemOrdering(item, anchor, Type.AFTER);
            }

            public enum Type {
                BEFORE,
                AFTER;
            }

        }

    }
    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }
}
