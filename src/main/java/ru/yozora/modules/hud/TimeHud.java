package ru.yozora.modules.hud;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeHud extends HudModule {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public TimeHud() {
        super("TimeHud");
        setEnabled(false);
    }

    @Override
    public String text() {
        return LocalTime.now().format(FMT);
    }
}
