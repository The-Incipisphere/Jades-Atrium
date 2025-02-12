package io.thedogofchaos.jadesatrium.common.data;

import com.tterrag.registrate.util.entry.RegistryEntry;

import io.thedogofchaos.jadesatrium.common.registry.CropRegistry;
import io.thedogofchaos.jadesatrium.item.OreHarvestedItem;
import io.thedogofchaos.jadesatrium.item.OreSeedItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;

import java.util.Objects;

import static io.thedogofchaos.jadesatrium.common.CommonProxy.REGISTRATE;
import static io.thedogofchaos.jadesatrium.common.data.ModCreativeTabs.MAIN_TAB;


public class ModItems {
    public static final RegistryEntry<OreHarvestedItem> BECQUERELLIUM_HARVESTED = CropRegistry.makeHarvestedItem(ModBlocks.becquerelliumCrop, ModBlocks.becquerelliumCrop.getCropInfo().getTextures().getTextureSetName());

    public static final RegistryEntry<OreSeedItem> BECQUERELLIUM_SEEDS = CropRegistry.makeSeedItem(ModBlocks.becquerelliumCrop);

    public static final RegistryEntry<ArmorItem> ANTI_TRAMPLE_BOOTS = REGISTRATE
            .item("anti_trample_boots", properties -> new ArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS, properties))
            .tab(Objects.requireNonNull(MAIN_TAB.getKey()))
            .register();

    public static void init() {
        ModBlocks.becquerelliumCrop.setHarvestedItem(BECQUERELLIUM_HARVESTED);
        ModBlocks.becquerelliumCrop.setSeedItem(BECQUERELLIUM_SEEDS);
    }
}
