package ru.yozora.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import ru.yozora.Yozora;
import ru.yozora.util.HudRenderer;

public class KeyInputHandler {
    private static final KeyBinding.Category CATEGORY =
            KeyBinding.Category.create(Identifier.of(Yozora.MOD_ID, "main"));

    public static KeyBinding GUI_KEY;
    public static KeyBinding ZOOM_KEY;

    public static void register() {
        GUI_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.yozora.gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                CATEGORY
        ));

        ZOOM_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.yozora.zoom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> HudRenderer.tick());
    }
}
