package com.swallows.additionalapi;

import com.swallows.additionalapi.command.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = "required-after:forge@[14.23.5.2847,);" + "required-after:crafttweaker@[4.0.4,);",
        acceptedMinecraftVersions = "[1.12, 1.13)"
)
public class AdditionalAPI {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER.info("Hello From {}!", Tags.MOD_NAME);
    }

    @Mod.EventHandler
    public void postInit(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public static void serverInit(FMLServerStartingEvent event)
    {

    }
}
