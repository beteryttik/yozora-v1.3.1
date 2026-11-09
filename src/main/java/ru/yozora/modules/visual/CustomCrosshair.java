package ru.yozora.modules.visual;

import ru.yozora.modules.Category;
import ru.yozora.modules.Module;

public class CustomCrosshair extends Module {
    public int size = 6;
    public int thickness = 1;
    public int gap = 2;
    public int color = 0xFF00FFAA;

    public CustomCrosshair() {
        super("CustomCrosshair", Category.VISUAL);
        setEnabled(false);
    }
}
