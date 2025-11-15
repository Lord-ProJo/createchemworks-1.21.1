package com.projo.chemworks;


import net.createmod.catnip.lang.Lang;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

@SuppressWarnings({"unused", "deprecation"})
public class CWTags {

        public static <T> TagKey<T> optionalTag(Registry<T> registry, ResourceLocation id) {
            return TagKey.create(registry.key(), id);
        }

    public enum NameSpace {
        MOD(CreateChemworks.MOD_ID),
        COMMON("c"),
        CREATE("create"),
        NEOFORGE(COMMON.id),
        MINECRAFT("minecraft")
        ;

        public final String id;
        public final boolean optionalDefault;
        public final boolean alwaysDatagenDefault;

        NameSpace(String id) {
            this(id, true, false);
        }

        NameSpace(String id, boolean optionalDefault, boolean alwaysDatagenDefault) {
            this.id = id;
            this.optionalDefault = optionalDefault;
            this.alwaysDatagenDefault = alwaysDatagenDefault;
        }
    }


    public enum CWBlockTags {
        SULFUR_ORES
        ;

        public final TagKey<Block> tag;
        public final boolean alwaysDatagen;

        CWBlockTags() {
            this(NameSpace.MOD);
        }

        CWBlockTags(NameSpace namespace) {
            this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        CWBlockTags(NameSpace nameSpace, String path) {
            this(nameSpace, path, nameSpace.optionalDefault, nameSpace.alwaysDatagenDefault);
        }

        CWBlockTags(NameSpace nameSpace, boolean optional, boolean alwaysDatagenDefault) {
            this(nameSpace, null, optional, alwaysDatagenDefault);
        }

        CWBlockTags(NameSpace nameSpace, String path, boolean optional, boolean alwaysDatagenDefault) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(nameSpace.id, path == null ? Lang.asId(name()) : path);
            if (optional) {
                tag = optionalTag(BuiltInRegistries.BLOCK, id);
            } else {
                tag = BlockTags.create(id);
            }
            this.alwaysDatagen = alwaysDatagenDefault;
        }

        public boolean matches(Block block) {
            return block.builtInRegistryHolder().is(tag);
        }

        public boolean matches(ItemStack stack) {
            return stack != null && stack.getItem() instanceof BlockItem blockItem && matches(blockItem.getBlock());
        }

        public boolean matches(BlockState state) {
            return state.is(tag);
        }

        private static void init() {}
    }



//Item Tag
    public enum CWItemTags {
        SULFUR_RAW_MATERIALS
        ;

        public final TagKey<Item> tag;
        public final boolean alwaysDatagen;

        CWItemTags() {
            this(NameSpace.MOD);
        }
        CWItemTags(NameSpace namespace) {
            this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        CWItemTags(NameSpace nameSpace, String path) {
            this(nameSpace, path, nameSpace.optionalDefault, nameSpace.alwaysDatagenDefault);
        }

        CWItemTags(NameSpace nameSpace, boolean optional, boolean alwaysDatagenDefault) {
            this(nameSpace, null, optional, alwaysDatagenDefault);
        }

        CWItemTags(NameSpace nameSpace, String path, boolean optional, boolean alwaysDatagenDefault) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(nameSpace.id, path == null ? Lang.asId(name()) : path);
            if (optional) {
                tag = optionalTag(BuiltInRegistries.ITEM, id);
            } else {
                tag = ItemTags.create(id);
            }
            this.alwaysDatagen = alwaysDatagenDefault;
        }

        public boolean matches(Item item) {
            return item.builtInRegistryHolder().is(tag);
        }

        public boolean matches(ItemStack stack) {
            return stack.is(tag);
        }

        private static void init() {}
    }
//Fluids Tag
    public enum CWFluidTags {
        //Write tag here
        ;

        public final TagKey<Fluid> tag;
        public final boolean alwaysDatagen;

        CWFluidTags() {
            this(NameSpace.MOD);
        }

        CWFluidTags(NameSpace namespace) {
            this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        CWFluidTags(NameSpace nameSpace, String path) {
            this(nameSpace, path, nameSpace.optionalDefault, nameSpace.alwaysDatagenDefault);
        }

        CWFluidTags(NameSpace nameSpace, boolean optional, boolean alwaysDatagenDefault) {
            this(nameSpace, null, optional, alwaysDatagenDefault);
        }

        CWFluidTags(NameSpace nameSpace, String path, boolean optional, boolean alwaysDatagenDefault) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(nameSpace.id, path == null ? Lang.asId(name()) : path);
            if (optional) {
                tag = optionalTag(BuiltInRegistries.FLUID, id);
            } else {
                tag = FluidTags.create(id);
            }
            this.alwaysDatagen = alwaysDatagenDefault;
        }

        public boolean matches(Fluid fluid) {
            return fluid.is(tag);
        }

        public boolean matches(FluidState stack) {
            return stack.is(tag);
        }

        private static void init() {}
    }

// Entity Tag
    public enum CWEntityTags {
        //Write Tag here
        ;

        public final TagKey<EntityType<?>> tag;
        public final boolean alwaysDatagen;

        CWEntityTags() {
            this(NameSpace.MOD);
        }

        CWEntityTags(NameSpace nameSpace) {
            this(nameSpace, nameSpace.optionalDefault, nameSpace.alwaysDatagenDefault);
        }

        CWEntityTags(NameSpace nameSpace, String path) {
            this(nameSpace, path, nameSpace.optionalDefault, nameSpace.alwaysDatagenDefault);
        }

        CWEntityTags(NameSpace nameSpace, boolean optional, boolean alwaysDatagenDefault) {
            this(nameSpace, null, optional, alwaysDatagenDefault);
        }

        CWEntityTags(NameSpace nameSpace, String path, boolean optional, boolean alwaysDatagenDefault) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(nameSpace.id, path == null ? Lang.asId(name()) : path);
            if (optional) {
                tag = optionalTag(BuiltInRegistries.ENTITY_TYPE, id);
            } else {
                tag = TagKey.create(Registries.ENTITY_TYPE, id);
            }
            this.alwaysDatagen = alwaysDatagenDefault;
        }

        public boolean matches(EntityType<?> type) {
            return type.is(tag);
        }

        public boolean matches(Entity entity) {
            return matches(entity.getType());
        }

        private static void init() {}
    }


    public static void init() {
        CreateChemworks.LOGGER.info("Registering mod tags for " + CreateChemworks.MOD_ID);
        CWBlockTags.init();
        CWItemTags.init();
        CWFluidTags.init();
        CWEntityTags.init();
    }
}
