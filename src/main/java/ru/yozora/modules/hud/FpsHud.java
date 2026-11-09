package ru.yozora.modules.hud;

public class FpsHud extends HudModule {
    public FpsHud() {
        super("FpsHud");
        setEnabled(true);
    }

    @Override
    public String text() {
        return ru.yozora.util.HudRenderer.fpsText();
    }

    @Override
    public int color() {
        return ru.yozora.util.HudRenderer.fpsColor();
    }
}
