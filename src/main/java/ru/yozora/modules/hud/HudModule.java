package ru.yozora.modules.hud;

import ru.yozora.modules.Category;
import ru.yozora.modules.Module;

public abstract class HudModule extends Module {
    public int x = 8;
    public int y = 8;

    public HudModule(String name) {
        super(name, Category.HUD);
    }

    public abstract String text();

    public int color() {
        return 0xFFFFFFFF;
    }
}
