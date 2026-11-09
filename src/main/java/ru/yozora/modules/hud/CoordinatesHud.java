package ru.yozora.modules.hud;

public class CoordinatesHud extends HudModule {
    public CoordinatesHud() {
        super("CoordinatesHud");
        setEnabled(true);
    }

    @Override
    public String text() {
        return ru.yozora.util.HudRenderer.coordinatesText();
    }
}
