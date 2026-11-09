package ru.yozora.modules.visual;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import ru.yozora.modules.Category;
import ru.yozora.modules.Module;

public class FullBright extends Module {
    private Double previousGamma = null;

    public FullBright() {
        super("FullBright", Category.VISUAL);
        setEnabled(false);
    }

    @Override
    protected void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        SimpleOption<Double> gamma = mc.options.getGamma();
        previousGamma = gamma.getValue();
        gamma.setValue(16.0);
    }

    @Override
    protected void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (previousGamma != null) {
            mc.options.getGamma().setValue(previousGamma);
        }
    }
}
