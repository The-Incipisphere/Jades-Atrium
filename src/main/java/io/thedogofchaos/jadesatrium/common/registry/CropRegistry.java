package io.thedogofchaos.jadesatrium.common.registry;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

import static io.thedogofchaos.jadesatrium.JadesAtrium.MOD_ID;
import static io.thedogofchaos.jadesatrium.common.CommonProxy.REGISTRATE;

/*
 * Based on BlakeBr0's crop registry system for Mystical Agriculture
 */
public class CropRegistry implements ICropRegistry {
    private static final CropRegistry INSTANCE = new CropRegistry();
    private final Map<String, RegistryEntry<OreCropBlock>> CROP_BLOCKS = new HashMap<>();
    private final Map<String, RegistryEntry<OreHarvestedItem>> CROP_HARVESTED_ITEMS = new HashMap<>();
    private final Map<String, RegistryEntry<OreSeedItem>> CROP_SEED_ITEMS = new HashMap<>();
    private final Map<ResourceLocation, Crop> CROPS = new LinkedHashMap<>();
    @Getter
    @Setter
    private boolean cropRegistryIsFrozen = false;

    public static CropRegistry getInstance() {
        return INSTANCE;
    }

    public static @NotNull BlockEntry<OreCropBlock> makeCropBlock(Crop crop) {
        return REGISTRATE
                .block(crop.getCropNameWithSuffix("crop"), properties -> new OreCropBlock(crop, properties))
                .initialProperties(() -> Blocks.WHEAT)
                .loot((lootTable, block) ->
                        // this may or may not work
                        lootTable.add(block, new LootTable.Builder()
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(crop.getHarvestedItem())
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(block.getAgeProperty(), 7)
                                                        )
                                                ).otherwise(LootItem.lootTableItem(crop.getSeedItem()))
                                        )
                                )
                                .withPool(LootPool.lootPool()
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(block.getAgeProperty(), 7)
                                                )
                                        )
                                        .add(LootItem.lootTableItem(crop.getSeedItem()))
                                )
                        )
                )
                .blockstate((context, provider) ->
                        BlockStateUtils.threeTextureCropCross(
                                provider.getVariantBuilder(context.get()),
                                provider,
                                crop,
                                context.get()
                        )
                )
                .color(() -> () -> (state, reader, pos, index) -> crop.getLayerARGB(index)) // unholy lambda chain
                .lang(RegistrateLangProvider.toEnglishName(crop.getCropNameWithSuffix("crop")))
                .register();
    }

    public static @NotNull ItemEntry<OreHarvestedItem> makeHarvestedItem(Crop crop, String textureSetName) {
        return REGISTRATE
                .item(crop.getCropNameWithSuffix("harvested"), properties -> new OreHarvestedItem(crop, properties))
                .initialProperties(Item.Properties::new)
                .model((context, provider) -> provider.generated(
                                context,
                                new ResourceLocation(MOD_ID, "block/plant_assets/crop/1_tall/" + textureSetName + "/age7/flower"),
                                new ResourceLocation(MOD_ID, "block/plant_assets/crop/1_tall/" + textureSetName + "/age7/pistil"),
                                new ResourceLocation(MOD_ID, "block/plant_assets/crop/1_tall/" + textureSetName + "/age7/stem")
                        )
                )
                .color(() -> () -> (itemStack, index) -> crop.getLayerARGB(index)) // unholy lambda chain 2
                .lang(RegistrateLangProvider.toEnglishName(crop.getCropNameWithPrefix("harvested")))
                .tab(Objects.requireNonNull(CROP_HARVESTED_TAB.getKey()))
                .register();
    }

    public static @NotNull ItemEntry<OreSeedItem> makeSeedItem(Crop crop) {
        return REGISTRATE
                .item(crop.getCropNameWithSuffix("seed"), properties -> new OreSeedItem(crop, properties))
                .initialProperties(Item.Properties::new)
                .model((context, provider) -> provider.generated(context, new ResourceLocation(MOD_ID, "item/plant_assets/crop/" + crop.getCropInfo().getTextures().getTextureSetName() + "/seed")))
                .color(() -> () -> (itemStack, index) -> crop.getLayerARGB(index)) // unholy lambda chain 3
                .lang(RegistrateLangProvider.toEnglishName(crop.getCropNameWithSuffix("seed")))
                .tab(Objects.requireNonNull(CROP_SEEDS_TAB.getKey()))
                .register();
    }

    public void register(Crop crop) {
        if (!this.cropRegistryIsFrozen) {
            if (this.CROPS.values().stream().noneMatch(c -> c.getCropName().equals(crop.getCropName()))) {
                this.CROPS.put(crop.getCropInfo().getId(), crop);
            } else {
                throw new DuplicateRegistryEntryException("Tried to register crop '"+crop.getCropInfo().getId()+"', but that crop has already been registered!");
            }
        } else {
            throw new FrozenRegistryException("Crop Registry has already been frozen! Tried to register crop: "+crop.getCropInfo().getId());
        }
    }

    public Map<ResourceLocation, Crop> getCrops() {
        return this.CROPS;
    }

    public Map<String, RegistryEntry<OreCropBlock>> getCropBlocksAsRegistryEntries() {
        return CROP_BLOCKS;
    }

    public Map<String, OreCropBlock> getCropBlocksAsOreCropBlock() {
        var list = new HashMap<String, OreCropBlock>();
        this.CROP_BLOCKS.forEach((str, crop) -> list.put(str, crop.get()));
        return list;
    }

    public Map<String, RegistryEntry<OreHarvestedItem>> getHarvestedItemsAsRegistryEntries() {
        return CROP_HARVESTED_ITEMS;
    }

    public Map<String, OreHarvestedItem> getHarvestedItemsAsOreHarvestedItem() {
        var list = new HashMap<String, OreHarvestedItem>();
        this.CROP_HARVESTED_ITEMS.forEach((str, crop) -> list.put(str, crop.get()));
        return list;

    }

    public Map<String, RegistryEntry<OreSeedItem>> getSeedItemsAsRegistryEntries() {
        return CROP_SEED_ITEMS;
    }

    public Map<String, OreSeedItem> getSeedItemsAsOreSeedItem() {
        var list = new HashMap<String, OreSeedItem>();
        this.CROP_SEED_ITEMS.forEach((str, crop) -> list.put(str, crop.get()));
        return list;
    }

    @Override
    public Crop getCropById(ResourceLocation id) {
        return this.CROPS.get(id);
    }

    @Override
    public Crop getCropByName(String name) {
        return this.CROPS.values().stream().filter(
                c -> name.equals(
                        c.getCropName()
                )
        ).findFirst().orElse(null);
    }

    public void generateCrops() {
        var crops = this.CROPS.values();
        crops.forEach(c -> {
            String textureSetName = c.getCropInfo().getTextures().getTextureSetName();
            if (c.getCropBlock() == null) {
                BlockEntry<OreCropBlock> cropBlockEntry = makeCropBlock(c);
                c.setCropBlock(cropBlockEntry);
                CROP_BLOCKS.put(c.getCropName(), cropBlockEntry);
            }
            if (c.getHarvestedItem() == null) {
                ItemEntry<OreHarvestedItem> harvestedItemEntry = makeHarvestedItem(c, textureSetName);
                c.setHarvestedItem(harvestedItemEntry);
                CROP_HARVESTED_ITEMS.put(c.getCropName(), harvestedItemEntry);
            }
            if (c.getSeedItem() == null) {
                ItemEntry<OreSeedItem> seedItemEntry = makeSeedItem(c);
                c.setSeedItem(seedItemEntry);
                CROP_SEED_ITEMS.put(c.getCropName(), seedItemEntry);
            }
        });
    }
}
