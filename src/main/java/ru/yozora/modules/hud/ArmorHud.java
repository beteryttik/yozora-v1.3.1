package ru.yozora.modules.hud;

public class ArmorHud extends HudModule {
    public ArmorHud() {
        super("ArmorHud");
        setEnabled(false);
    }

    @Override
    public String text() {
        return ru.yozora.util.HudRenderer.armorText();
    }
}
