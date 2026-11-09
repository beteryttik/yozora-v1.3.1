package ru.yozora.modules.hud;

public class PingHud extends HudModule {
    public PingHud() {
        super("PingHud");
        setEnabled(false);
    }

    @Override
    public String text() {
        return ru.yozora.util.HudRenderer.pingText();
    }
}
