package ru.yozora.modules.opt;

import ru.yozora.modules.Category;
import ru.yozora.modules.Module;

public class ParticleCull extends Module {
    public static int RADIUS = 48;

    public ParticleCull() {
        super("ParticleCull", Category.OPT);
        setEnabled(true);
    }

    public static boolean shouldSkip(double dx, double dy, double dz) {
        if (!ru.yozora.Yozora.module("ParticleCull").isEnabled()) return false;
        return dx * dx + dy * dy + dz * dz > (double) RADIUS * RADIUS;
    }
}
