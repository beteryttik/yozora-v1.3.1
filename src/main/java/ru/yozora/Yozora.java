package ru.yozora;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yozora.command.YozoraCommand;
import ru.yozora.config.ConfigManager;
import ru.yozora.event.HudManager;
import ru.yozora.event.KeyInputHandler;
import ru.yozora.gui.ClickGuiScreen;
import ru.yozora.modules.Module;
import ru.yozora.modules.ModuleManager;
import ru.yozora.modules.hud.*;
import ru.yozora.modules.opt.*;
import ru.yozora.modules.visual.*;

public class Yozora implements ClientModInitializer {
    public static final String MOD_ID = "yozora";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static ModuleManager modules;
    private static ConfigManager config;

    public static ModuleManager modules() {
        return modules;
    }

    public static ConfigManager config() {
        return config;
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("[Yozora] initializing...");

        modules = new ModuleManager();
        config = new ConfigManager("yozora.json");

        // optimizations
        modules.add(new ParticleCull());
        modules.add(new EntityCull());
        modules.add(new NoClouds());
        modules.add(new FpsLimiter());

        // hud
        modules.add(new FpsHud());
        modules.add(new CoordinatesHud());
        modules.add(new PingHud());
        modules.add(new KeystrokesHud());
        modules.add(new DirectionHud());
        modules.add(new SpeedHud());
        modules.add(new ServerHud());
        modules.add(new TimeHud());
        modules.add(new ArmorHud());

        // visual
        modules.add(new Zoom());
        modules.add(new FullBright());
        modules.add(new NoHurtCam());
        modules.add(new CustomCrosshair());
        modules.add(new SafeWalk());

        KeyInputHandler.register();
        HudManager.register();
        YozoraCommand.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (KeyInputHandler.GUI_KEY.wasPressed()) {
                client.setScreen(new ClickGuiScreen());
            }
        });

        config.load(modules);

        LOGGER.info("[Yozora] loaded {} modules", modules.all().size());
    }

    public static void save() {
        config.save(modules);
    }

    public static Module module(String name) {
        return modules.byName(name);
    }
}
