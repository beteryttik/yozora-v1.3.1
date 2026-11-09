package ru.yozora.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public final class HudRenderer {
    private static int frameCount = 0;
    private static long lastFpsTime = 0;
    private static int fps = 0;
    private static long lastMoveTime = 0;
    private static double lastX = 0, lastZ = 0;
    private static double speedBlocksPerSecond = 0;

    private HudRenderer() {}

    public static void tick() {
        frameCount++;
        long now = System.currentTimeMillis();
        if (now - lastFpsTime >= 1000) {
            fps = frameCount;
            frameCount = 0;
            lastFpsTime = now;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null && now - lastMoveTime >= 1000) {
            double dx = mc.player.getX() - lastX;
            double dz = mc.player.getZ() - lastZ;
            speedBlocksPerSecond = MathHelper.sqrt(dx * dx + dz * dz);
            lastX = mc.player.getX();
            lastZ = mc.player.getZ();
            lastMoveTime = now;
        }
    }

    public static String fpsText() {
        return "FPS: " + fps;
    }

    public static int fpsColor() {
        if (fps >= 100) return 0xFF55FF55;
        if (fps >= 60) return 0xFFFFFF55;
        return 0xFFFF5555;
    }

    public static String coordinatesText() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return "XYZ: -";
        return String.format("XYZ: %d / %d / %d",
                (int) mc.player.getX(), (int) mc.player.getY(), (int) mc.player.getZ());
    }

    public static String pingText() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.getNetworkHandler() == null || mc.player == null) return "Ping: -";
        PlayerListEntry entry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
        if (entry == null) return "Ping: -";
        return "Ping: " + entry.getLatency() + "ms";
    }

    public static String keystrokesText() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(mc.options.forwardKey.isPressed() ? "W" : "_");
        sb.append(mc.options.backKey.isPressed() ? "S" : "_");
        sb.append(mc.options.leftKey.isPressed() ? "A" : "_");
        sb.append(mc.options.rightKey.isPressed() ? "D" : "_");
        sb.append(mc.options.jumpKey.isPressed() ? " [SPACE] " : " ");
        return sb.toString();
    }

    public static String directionText() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return "Dir: -";
        float yaw = mc.player.getYaw();
        yaw = MathHelper.wrapDegrees(yaw);
        String dir;
        if (yaw >= -45 && yaw < 45) dir = "South (+Z)";
        else if (yaw >= 45 && yaw < 135) dir = "West (-X)";
        else if (yaw >= -135 && yaw < -45) dir = "East (+X)";
        else dir = "North (-Z)";
        return "Dir: " + dir;
    }

    public static String speedText() {
        return String.format("Speed: %.2f m/s", speedBlocksPerSecond);
    }

    public static String serverText() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.getCurrentServerEntry() == null) return "Server: Singleplayer";
        return "Server: " + mc.getCurrentServerEntry().address;
    }

    public static String armorText() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return "Armor: -";
        int totalDurability = 0;
        int maxDurability = 0;
        for (ItemStack stack : mc.player.getInventory().getArmorItems()) {
            if (stack == null || stack.isEmpty()) continue;
            totalDurability += stack.getMaxDamage() - stack.getDamage();
            maxDurability += stack.getMaxDamage();
        }
        if (maxDurability == 0) return "Armor: empty";
        return "Armor: " + totalDurability + "/" + maxDurability;
    }
}
