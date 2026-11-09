package ru.yozora.modules.hud;

public class SpeedHud extends HudModule {
    public SpeedHud() {
        super("SpeedHud");
        setEnabled(true);
    }

    @Override
    public String text() {
        return ru.yozora.util.HudRenderer.speedText();
    }
}
