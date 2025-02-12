package io.thedogofchaos.jadesatrium.common;

import io.thedogofchaos.jadesatrium.common.registry.CropRegistry;
import net.minecraftforge.eventbus.api.IEventBus;

public class CommonRegistry {
    public static void init(IEventBus modEventBus) {
        CropRegistry cropRegistry = CropRegistry.getInstance();

        ModCreativeTabs.init(modEventBus);
        cropRegistry.setCropRegistryIsFrozen(false); // allow crop registration past this point
        ModPlants.init();
        cropRegistry.generateCrops();
        ModBlocks.init();
        ModItems.init();
        cropRegistry.setCropRegistryIsFrozen(true); // disallow crop registration past this point
    }
}
