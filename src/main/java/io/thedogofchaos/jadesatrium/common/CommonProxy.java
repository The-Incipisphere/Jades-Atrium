package io.thedogofchaos.jadesatrium.common;

import com.tterrag.registrate.Registrate;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.format.ConfigFormats;
import io.thedogofchaos.jadesatrium.JA_Config;
import io.thedogofchaos.jadesatrium.JadesAtrium;
import io.thedogofchaos.jadesatrium.common.network.Network;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static io.thedogofchaos.jadesatrium.JadesAtrium.LOGGER;

public class CommonProxy {
    public static Registrate REGISTRATE;
    public final FMLJavaModLoadingContext modLoadingContext = FMLJavaModLoadingContext.get();
    public final IEventBus modEventBus = modLoadingContext.getModEventBus();
    public static JA_Config config;

    public CommonProxy() {
        LOGGER.info("CommonProxy loading!");
        // Always register configs first.
        config = Configuration.registerConfig(JA_Config.class, ConfigFormats.yaml()).getConfigInstance();

        modEventBus.register(this);
    }

    public void init(IEventBus modEventBus) {
        REGISTRATE = Registrate.create(JadesAtrium.MOD_ID);
        Network.init();
        CommonRegistry.init(modEventBus);
    }
}
