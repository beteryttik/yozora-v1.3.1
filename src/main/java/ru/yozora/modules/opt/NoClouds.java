package ru.yozora.modules.opt;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;
import ru.yozora.modules.Category;
import ru.yozora.modules.Module;

public class NoClouds extends Module {
    private CloudRenderMode previous;

    public NoClouds() {
        super("NoClouds", Category.OPT);
        setEnabled(false);
    }

    @Override
    protected void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        previous = mc.options.getCloudsValue();
        mc.options.setCloudsValue(CloudRenderMode.OFF);
    }

    @Override
    protected void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (previous != null) {
            mc.options.setCloudsValue(previous);
        }
    }
}
