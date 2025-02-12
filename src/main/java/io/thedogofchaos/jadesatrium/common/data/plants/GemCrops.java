package io.thedogofchaos.jadesatrium.common.data.plants;

import io.thedogofchaos.jadesatrium.organic.Crop;
import io.thedogofchaos.jadesatrium.organic.CropTextures;

import static io.thedogofchaos.jadesatrium.JadesAtrium.id;
import static io.thedogofchaos.jadesatrium.common.data.ModPlants.*;

public class GemCrops {
    public static void init() {
        Ruby = new Crop.Builder(id("ruby"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xD72310)
                .setPistilColor(0x960B6D)
                .buildAndRegister();
        Sapphire = new Crop.Builder(id("sapphire"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0x3235E3)
                .setPistilColor(0x211455)
                .buildAndRegister();
        GreenSapphire = new Crop.Builder(id("green_sapphire"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0x9AE6B0)
                .setPistilColor(0x64C882)
                .buildAndRegister();
        Topaz = new Crop.Builder(id("topaz"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xE8D73A)
                .setPistilColor(0xF4680F)
                .buildAndRegister();
        Apatite = new Crop.Builder(id("apatite"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0x06CDF1)
                .setPistilColor(0x701C07)
                .buildAndRegister();
        Opal = new Crop.Builder(id("opal"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xF9E3EA)
                .setPistilColor(0x16BBE0)
                .buildAndRegister();
        Cinnabar = new Crop.Builder(id("cinnabar"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xFF335F)
                .setPistilColor(0x3F0110)
                .buildAndRegister();
        Realgar = new Crop.Builder(id("realgar"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xFF3D33)
                .setPistilColor(0x3F0110)
                .buildAndRegister();
        CertusQuartz = new Crop.Builder(id("certus_quartz"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xC2D6FF)
                .setPistilColor(0x86BACF)
                .buildAndRegister();
        Quartzite = new Crop.Builder(id("quartzite"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xF2F5ED)
                .setPistilColor(0xB8E2B8)
                .buildAndRegister();
        Salt = new Crop.Builder(id("salt"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xFAFAFA)
                .buildAndRegister();
    }
}
