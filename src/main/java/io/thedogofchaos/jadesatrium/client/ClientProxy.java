package io.thedogofchaos.jadesatrium.client;

import io.thedogofchaos.jadesatrium.common.CommonProxy;
import net.minecraftforge.eventbus.api.IEventBus;

import static io.thedogofchaos.jadesatrium.JadesAtrium.LOGGER;
import static io.thedogofchaos.jadesatrium.JadesAtrium.MOD_ID;

public class ClientProxy extends CommonProxy {
    public ClientProxy(){
        super();
        LOGGER.info("ClientProxy loading!");
    }

    @Override
    public void init(IEventBus modEventBus) {
        super.init(modEventBus);
        initLang();
    }

    private void initLang() {
        REGISTRATE.addRawLang("config.jade.plugin_" + MOD_ID + ".crop_invalid_biome", "Show crop biome restriction tooltip");
        REGISTRATE.addRawLang("tooltip." + MOD_ID + ".invalid_biome", "Can't grow in this biome!");
    }
}
