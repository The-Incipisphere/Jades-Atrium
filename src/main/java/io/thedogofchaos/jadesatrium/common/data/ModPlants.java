package io.thedogofchaos.jadesatrium.common.data;


import io.thedogofchaos.jadesatrium.common.data.plants.GemCrops;
import io.thedogofchaos.jadesatrium.common.data.plants.MetalCrops;
import io.thedogofchaos.jadesatrium.common.data.plants.MiscCrops;
import io.thedogofchaos.jadesatrium.common.data.plants.VanillaCrops;
import io.thedogofchaos.jadesatrium.organic.Crop;

// todo: assign all the fields in this class
public class ModPlants {
    // Vanilla
    public static Crop Gold;
    public static Crop Coal;
    public static Crop Redstone;
    public static Crop Diamond;
    public static Crop Emerald;
    public static Crop Amethyst;
    public static Crop Quartz;
    // GregTech
    // METALS
    public static Crop Magnetite;
    public static Crop Chalcopyrite;
    public static Crop Cassiterite;
    public static Crop Nickel;
    public static Crop Silver;
    public static Crop Cobalt;
    public static Crop Galena;
    public static Crop Sphalerite;
    public static Crop Bauxite;
    // GEMS
    public static Crop Ruby;
    public static Crop Sapphire;
    public static Crop GreenSapphire;
    public static Crop Topaz;
    public static Crop Apatite;
    public static Crop Opal;
    public static Crop Cinnabar;
    public static Crop Realgar;
    public static Crop CertusQuartz; // Maybe.
    public static Crop Quartzite;
    public static Crop Salt;
    // MISC
    public static Crop Saltpetre;
    public static Crop Sulphur;
    // Other
    public static Crop Becquerellium;

    public static void init() {
        VanillaCrops.init();
        MetalCrops.init();
        GemCrops.init();
        MiscCrops.init();
    }
}
