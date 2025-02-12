package io.thedogofchaos.jadesatrium.block;

import io.thedogofchaos.jadesatrium.organic.Crop;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * The common class for ALL OreCrops.
 */
public class OreCropBlock extends CropBlock implements ICropProvider {
    private final Crop crop;

    public OreCropBlock(Crop crop, Properties properties) {
        super(properties);
        this.crop = crop;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return false;
    }

    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getBlock() instanceof FarmBlock;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this.crop.getSeedItem();
    }

    @Override
    public Crop getCrop() {
        return this.crop;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!this.canGrow(level, pos))
            return;
        super.randomTick(state, level, pos, random);
    }

    private boolean canGrow(Level level, BlockPos pos) {
        Set<ResourceLocation> biomes = this.crop.getCropInfo().getRequiredBiomes();
        if (!biomes.isEmpty()) {
            Holder<Biome> biome = level.getBiome(pos);
            return biomes.stream().anyMatch(biome::is);
        }
        return true;
    }
}
