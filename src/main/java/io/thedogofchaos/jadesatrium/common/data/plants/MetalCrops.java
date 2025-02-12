package io.thedogofchaos.jadesatrium.common.data.plants;

import io.thedogofchaos.jadesatrium.organic.Crop;
import io.thedogofchaos.jadesatrium.organic.CropTextures;

import static io.thedogofchaos.jadesatrium.JadesAtrium.id;
import static io.thedogofchaos.jadesatrium.common.data.ModPlants.*;

public class MetalCrops {
    public static void init() {
        // GregTech
        Magnetite = new Crop.Builder(id("magnetite"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0x9D9D9D)
                .setPistilColor(0x06070E)
                .buildAndRegister();
        Chalcopyrite = new Crop.Builder(id("chalcopyrite"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0x96C185)
                .setPistilColor(0xE3AF1A)
                .buildAndRegister();
        Cassiterite = new Crop.Builder(id("cassiterite"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0x89847E)
                .setPistilColor(0x3B3B35)
                .buildAndRegister();
        Nickel = new Crop.Builder(id("nickel"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xCCDFF5)
                .setPistilColor(0x59563A)
                .buildAndRegister();
        Silver = new Crop.Builder(id("silver"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xDCDCFF)
                .setPistilColor(0x5A4705)
                .buildAndRegister();
        Cobalt = new Crop.Builder(id("cobalt"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0x5050FA)
                .setPistilColor(0x2D2D7A)
                .buildAndRegister();
        Galena = new Crop.Builder(id("galena"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xF3E8FA)
                .setPistilColor(0x331D42)
                .buildAndRegister();
        Sphalerite = new Crop.Builder(id("sphalerite"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xFFDC88)
                .setPistilColor(0x0F1605)
                .buildAndRegister();
        Bauxite = new Crop.Builder(id("bauxite"))
                .setTextures(CropTextures.DEBUG)
                .setFlowerColor(0xCFB853)
                .setPistilColor(0xE6220C)
                .buildAndRegister();
    }
}
