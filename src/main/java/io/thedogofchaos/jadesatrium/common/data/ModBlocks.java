package io.thedogofchaos.jadesatrium.common.data;

import com.tterrag.registrate.util.entry.RegistryEntry;

import io.thedogofchaos.jadesatrium.block.OreCropBlock;
import io.thedogofchaos.jadesatrium.common.registry.CropRegistry;
import io.thedogofchaos.jadesatrium.organic.Crop;

public class ModBlocks {
    public static final Crop becquerelliumCrop = ModPlants.Becquerellium; // shitty hack because of my own code.

    public static final RegistryEntry<OreCropBlock> BECQUERELLIUM_CROP = CropRegistry.makeCropBlock(becquerelliumCrop);

    public static void init() {
        becquerelliumCrop.setCropBlock(BECQUERELLIUM_CROP);
    }

}
