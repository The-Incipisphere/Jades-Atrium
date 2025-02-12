package io.thedogofchaos.jadesatrium.common.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static io.thedogofchaos.jadesatrium.JadesAtrium.*;
import static io.thedogofchaos.jadesatrium.common.CommonProxy.REGISTRATE;


public class ModCreativeTabs {
    // Doing this as just a normal DeferredRegister because Registrate’s creative tab registration is strange as fuck.
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<CreativeModeTab> CROP_HARVESTED_TAB = CREATIVE_TABS.register("crop_harvested_items_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.BECQUERELLIUM_HARVESTED.get()))
            .title(REGISTRATE.addLang("itemGroup", id("crop_harvested_items_tab"), MOD_NAME + " Harvested Crops"))
            .build()
    );
    public static final RegistryObject<CreativeModeTab> CROP_SEEDS_TAB = CREATIVE_TABS.register("crop_seed_items_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.BECQUERELLIUM_SEEDS.get()))
            .title(REGISTRATE.addLang("itemGroup", id("crop_seed_items_tab"), MOD_NAME + " Crop Seeds"))
            .build()
    );
    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_TABS.register(MOD_ID + "_tab", () -> CreativeModeTab.builder()
            // .icon(() -> new ItemStack(ModItems.BECQUERELLIUM_SEEDS.get()))
            .title(REGISTRATE.addLang("itemGroup", id(MOD_ID + "_tab"), MOD_NAME + " Blocks/Items"))
            .build()
    );

    public static void init(IEventBus modBus) {
        CREATIVE_TABS.register(modBus);
    }
}
