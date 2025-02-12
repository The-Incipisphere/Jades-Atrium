package io.thedogofchaos.jadesatrium.organic;

import io.thedogofchaos.jadesatrium.common.registry.CropRegistry;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.CropBlock;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class Crop {

    @NotNull
    @Getter
    private final CropInfo cropInfo;

    // do not lombok these three fields
    private Supplier<? extends CropBlock> cropBlock;
    private Supplier<? extends Item> harvestedItem;
    private Supplier<? extends ItemNameBlockItem> seedItem;

    protected Crop(ResourceLocation id) {
        cropInfo = new CropInfo(id);
    }

    public Crop(@NotNull CropInfo cropInfo) {
        this.cropInfo = cropInfo;
    }

    /**
     * Gets the name of the {@link Crop} object that this method is called on.
     *
     * @return The all-lowercase assigned id/name of this crop, of type {@link String}.
     */
    public String getCropName() {
        return this.cropInfo.id.getPath();
    }

    /**
     * Gets the name of the {@link Crop} object that this method is called on,
     * appended with an underscore-separated suffix of one’s own choice.
     *
     * @param suffix The suffix, of type {@link String}, to append to the end of the crop’s name.
     * @return The all-lowercase assigned name of this crop, with an underscore-separated suffix applied, of type {@link String}.
     */
    public String getCropNameWithSuffix(String suffix) {
        return String.format("%s_%s", this.getCropName(), suffix);
    }

    /**
     * Gets the name of the {@link Crop} object that this method is called on,
     * prepended with an underscore-separated prefix of one’s own choice.
     *
     * @param prefix The prefix, of type {@link String}, to prepend to the beginning of the crop’s name.
     * @return The all-lowercase assigned name of this crop, with an underscore-separated prefix applied, of type {@link String}.
     */
    public String getCropNameWithPrefix(String prefix) {
        return String.format("%s_%s", prefix, this.getCropName());
    }

    /**
     * Retrieves the ARGB colour value for a specified layer index from the crop’s colour data.
     *
     * <p>This method calculates the appropriate layer index if the provided index is below -100.
     * If the calculated or provided index is out of bounds, it returns -1 as an invalid colour value.
     * For valid indices, the method attempts to fetch the ARGB colour value from the {@code colors} {@link IntList}.
     * If the colour at the specified index is invalid (-1), and the index is not 0, it falls back
     * to the colour of the first layer (index 0).
     *
     * <p><b>In simpler terms:</b> This method gives you the colour of a specific layer.
     * If the index is too low, it fixes it.
     * If the index is out of range, or the layer doesn’t have a
     * valid colour, it either uses the first layer’s colour or says there’s no valid colour (-1).
     *
     * @param layerIndex the index of the layer whose colour value is to be retrieved.
     *                   If the index is less than -100, it is normalised to a valid index by using
     *                   {@code (Math.abs(layerIndex) % 100) / 10}.
     * @return the ARGB colour value of the specified layer.
     * If the index is out of bounds, or the
     * specified colour is invalid, returns -1.
     */
    public int getLayerARGB(int layerIndex) {
        if (layerIndex < -100) {
            layerIndex = (Math.abs(layerIndex) % 100) / 10;
        }
        if (layerIndex > cropInfo.colors.size() - 1 || layerIndex < 0) return -1;
        int layerColor = cropInfo.colors.getInt(layerIndex);
        if (layerColor != -1 || layerIndex == 0) return layerColor;
        else return cropInfo.colors.getInt(0);
    }

    /**
     * Directly gets the crop block of the given crop (NOT the supplier containing it)
     *
     * @return The crop block (of type {@link CropBlock}) assigned to this crop, or <b>{@link null}</b> if it is not present.
     */
    public CropBlock getCropBlock() {
        return this.cropBlock == null ? null : this.cropBlock.get();
    }

    /**
     * Sets the crop block of the {@link Crop} object that this method was called on.
     *
     * @param cropBlock Anything extending from {@link CropBlock} that you wish to set as the crop block of this crop.
     * @return The {@link Crop} object that this method was called on.
     */
    public Crop setCropBlock(Supplier<? extends CropBlock> cropBlock) {
        this.cropBlock = cropBlock;
        return this;
    }

    /**
     * Directly gets the harvested item of the given crop (NOT the supplier containing it)
     *
     * @return The harvested item (of type {@link Item}) assigned to this crop, or <b>{@link null}</b> if it is not present.
     */
    public Item getHarvestedItem() {
        return this.harvestedItem == null ? null : this.harvestedItem.get();
    }

    /**
     * Sets the harvested item of the {@link Crop} object that this method was called on.
     *
     * @param harvestedItem Anything extending from {@link Item} that you wish to set as the harvested item of this crop.
     * @return The {@link Crop} object that this method was called on.
     */
    public Crop setHarvestedItem(Supplier<? extends Item> harvestedItem) {
        this.harvestedItem = harvestedItem;
        return this;
    }

    /**
     * Directly gets the seed item of the given crop (NOT the supplier containing it)
     *
     * @return The seed item (of type {@link ItemNameBlockItem}) assigned to this crop, or <b>{@link null}</b> if it is not present.
     */
    public ItemNameBlockItem getSeedItem() {
        return this.seedItem == null ? null : this.seedItem.get();
    }

    /**
     * Sets the seed item of the {@link Crop} object that this method was called on.
     *
     * @param seedItem Anything extending from {@link ItemNameBlockItem} that you wish to set as the seed item of this crop.
     * @return The {@link Crop} object that this method was called on.
     */
    public Crop setSeedItem(Supplier<? extends ItemNameBlockItem> seedItem) {
        this.seedItem = seedItem;
        return this;
    }

    /**
     * Registers this crop object with the {@link CropRegistry}.<br>
     * This method should <b>ONLY</b> ever be called by {@code buildAndRegister()} in the Builder class of this class.
     *
     * @see Builder#buildAndRegister()
     */
    private void registerCrop() {
        CropRegistry.getInstance().register(this);
    }

    /**
     * The main builder class for crops. New crops can <b>ONLY</b> be created through this builder.
     */
    public static class Builder {
        private final CropInfo cropInfo;

        public Builder(ResourceLocation id) {
            if (id.getPath().charAt(id.getPath().length() - 1) == '_')
                throw new IllegalArgumentException("Plant name cannot end with a '_'!");
            cropInfo = new CropInfo(id);
        }

        public Builder setTextures(CropTextures textures) {
            cropInfo.textures = textures;
            return this;
        }

        public Builder setFlowerColor(int flowerColor) {
            this.cropInfo.colors.set(0, flowerColor);
            return this;
        }

        public Builder setPistilColor(int pistilColor) {
            this.cropInfo.colors.set(1, pistilColor);
            return this;
        }

        public Builder setStemColor(int stemColor) {
            this.cropInfo.colors.set(2, stemColor);
            return this;
        }

        public Builder setSeedColor(int seedColor) {
            this.cropInfo.colors.set(3, seedColor);
            return this;
        }

        public Builder setRequiredBiomes(ResourceLocation... biomeIds) {
            // I wish it was possible to validate the biomes to be assigned at load-time, but biomes are datapacks.
            cropInfo.requiredBiomes.addAll(Arrays.asList(biomeIds));
            return this;
        }

        public Crop buildAndRegister() {
            //?: Sets the color of the pistil to a lighter version of the flower color, if not set.
            if (cropInfo.colors.getInt(1) == -1) cropInfo.colors.set(1, new Color(cropInfo.colors.getInt(0)).brighter().getRGB());
            //?: Sets the color of seeds to the flower color, if not set.
            if (cropInfo.colors.getInt(3) == -1) cropInfo.colors.set(3, cropInfo.colors.getInt(0));
            //?: Sets the texture set to the debug textures, if not set.
            if (cropInfo.textures == null) cropInfo.textures = CropTextures.DEBUG;
            var crop = new Crop(cropInfo);
            crop.registerCrop();
            return crop;
        }

        public Crop buildButDontRegister() {
            return new Crop(cropInfo);
        }
    }

    /**
     * This class holds key information about any given crop, such as its name,
     * its required biomes, its colours, and its assigned set of textures.
     */
    public static class CropInfo {
        /**
         * The internal name of the crop being registered. <b>CANNOT BE NULL.</b>
         */
        @Getter
        @NotNull
        private final ResourceLocation id;
        @Getter
        private final Set<ResourceLocation> requiredBiomes;
        @Getter
        @Setter
        private IntList colors = new IntArrayList(List.of(-1, -1, -1, -1));
        @Getter
        private CropTextures textures;

        private CropInfo(@NotNull ResourceLocation id) {
            this.id = id;
            requiredBiomes = new HashSet<>();
            colors.set(0, 0x808080); // DEFAULT FLOWER COLOR
            colors.set(2, 0x177B04); // DEFAULT STEM COLOR
        }
    }
}
