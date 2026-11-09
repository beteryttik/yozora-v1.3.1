package ru.yozora.modules.hud;

public class ServerHud extends HudModule {
    public ServerHud() {
        super("ServerHud");
        setEnabled(false);
    }

    @Override
    public String text() {
        return ru.yozora.util.HudRenderer.serverText();
    }
}
