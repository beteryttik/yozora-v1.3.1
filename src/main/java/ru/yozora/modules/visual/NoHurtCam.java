package ru.yozora.modules.visual;

import ru.yozora.modules.Category;
import ru.yozora.modules.Module;

public class NoHurtCam extends Module {
    public NoHurtCam() {
        super("NoHurtCam", Category.VISUAL);
        setEnabled(true);
    }

    public static boolean shouldCancel() {
        return ru.yozora.Yozora.module("NoHurtCam").isEnabled();
    }
}
