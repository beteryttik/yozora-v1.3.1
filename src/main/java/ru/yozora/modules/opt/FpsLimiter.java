package ru.yozora.modules.opt;

import net.minecraft.client.MinecraftClient;
import ru.yozora.modules.Category;
import ru.yozora.modules.Module;

public class FpsLimiter extends Module {
    private long lastFrame = 0;

    public FpsLimiter() {
        super("FpsLimiter", Category.OPT);
        setEnabled(false);
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null) return;

        long frameTimeNanos = 1_000_000_000L / 60L;
        long now = System.nanoTime();
        long delta = now - lastFrame;

        if (delta < frameTimeNanos) {
            Thread.onSpinWait();
            return;
        }
        lastFrame = now;
    }
}
