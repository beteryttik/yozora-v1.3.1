package ru.yozora.modules.visual;

import net.minecraft.client.MinecraftClient;
import ru.yozora.modules.Category;
import ru.yozora.modules.Module;
import ru.yozora.event.KeyInputHandler;

public class Zoom extends Module {
    public static double FACTOR = 4.0;

    public Zoom() {
        super("Zoom", Category.VISUAL);
        setEnabled(true);
    }

    public static boolean active() {
        return ru.yozora.Yozora.module("Zoom").isEnabled()
                && KeyInputHandler.ZOOM_KEY.isPressed();
    }
}
