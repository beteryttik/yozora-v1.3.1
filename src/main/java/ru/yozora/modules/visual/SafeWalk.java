package ru.yozora.modules.visual;

import ru.yozora.modules.Category;
import ru.yozora.modules.Module;

public class SafeWalk extends Module {
    public SafeWalk() {
        super("SafeWalk", Category.VISUAL);
        setEnabled(false);
    }

    public static boolean active() {
        return ru.yozora.Yozora.module("SafeWalk").isEnabled();
    }
}
