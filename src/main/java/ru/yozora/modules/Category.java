package ru.yozora.modules;

public enum Category {
    OPT("Optimization"),
    HUD("HUD"),
    VISUAL("Visual");

    public final String title;

    Category(String title) {
        this.title = title;
    }
}
