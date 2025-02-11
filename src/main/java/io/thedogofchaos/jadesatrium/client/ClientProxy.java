package io.thedogofchaos.jadesatrium.client;

import io.thedogofchaos.jadesatrium.common.CommonProxy;
import net.minecraftforge.eventbus.api.IEventBus;

public class ClientProxy extends CommonProxy {
    public ClientProxy(){
        super();
    }

    @Override
    public void init(IEventBus modEventBus) {
        super.init(modEventBus);
        // Do client-specific stuff here
    }
}
