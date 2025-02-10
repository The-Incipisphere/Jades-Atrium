package io.thedogofchaos.jadesatrium;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import io.thedogofchaos.jadesatrium.common.CommonProxy;
import io.thedogofchaos.jadesatrium.client.ClientProxy;
import org.slf4j.Logger;

@Mod(JadesAtrium.MOD_ID)
public class JadesAtrium {
    public static final String MOD_ID = "jadesatrium";
    public static final String MOD_NAME = "Jade's Atrium";

    private static final Logger LOGGER = LogUtils.getLogger();

    public final FMLJavaModLoadingContext modLoadingContext = FMLJavaModLoadingContext.get();
    public final IEventBus modBus = modLoadingContext.getModEventBus();

    public JadesAtrium() {
        JadesAtrium.init();
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }

    public static void init() {
        LOGGER.info("We're loading {} on the {}", MOD_NAME, FMLEnvironment.dist);
    }
}
