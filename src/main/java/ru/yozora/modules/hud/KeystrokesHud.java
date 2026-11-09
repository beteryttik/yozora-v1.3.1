package ru.yozora.modules.hud;

public class KeystrokesHud extends HudModule {
    public KeystrokesHud() {
        super("KeystrokesHud");
        setEnabled(false);
    }

    @Override
    public String text() {
        return ru.yozora.util.HudRenderer.keystrokesText();
    }
}
