package io.thedogofchaos.jadesatrium.client;

import io.thedogofchaos.jadesatrium.common.CommonProxy;
import net.minecraftforge.eventbus.api.IEventBus;

import static io.thedogofchaos.jadesatrium.JadesAtrium.LOGGER;

public class ClientProxy extends CommonProxy {
    public ClientProxy(){
        super();
        LOGGER.info("ClientProxy loading!");
    }

    @Override
    public void init(IEventBus modEventBus) {
        super.init(modEventBus);
        // Do client-specific stuff here
    }
}
