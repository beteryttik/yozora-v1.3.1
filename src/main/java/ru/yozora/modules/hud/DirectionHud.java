package ru.yozora.modules.hud;

public class DirectionHud extends HudModule {
    public DirectionHud() {
        super("DirectionHud");
        setEnabled(true);
    }

    @Override
    public String text() {
        return ru.yozora.util.HudRenderer.directionText();
    }
}
