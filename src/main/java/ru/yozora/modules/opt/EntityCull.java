package ru.yozora.modules.opt;

import ru.yozora.modules.Category;
import ru.yozora.modules.Module;

public class EntityCull extends Module {
    public static int RADIUS = 64;

    public EntityCull() {
        super("EntityCull", Category.OPT);
        setEnabled(true);
    }

    public static boolean shouldSkip(double dx, double dy, double dz) {
        if (!ru.yozora.Yozora.module("EntityCull").isEnabled()) return false;
        return dx * dx + dy * dy + dz * dz > (double) RADIUS * RADIUS;
    }
}
