package ru.yozora.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.yozora.Yozora;
import ru.yozora.modules.Module;
import ru.yozora.modules.hud.HudModule;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Path file;

    public ConfigManager(String name) {
        Path dir = Path.of("config");
        try {
            Files.createDirectories(dir);
        } catch (IOException ignored) {}
        this.file = dir.resolve(name);
    }

    public void save(ru.yozora.modules.ModuleManager modules) {
        JsonObject root = new JsonObject();
        for (Module m : modules.all()) {
            JsonObject mo = new JsonObject();
            mo.addProperty("enabled", m.isEnabled());
            if (m instanceof HudModule hud) {
                mo.addProperty("x", hud.x);
                mo.addProperty("y", hud.y);
            }
            root.add(m.getName(), mo);
        }
        try {
            Files.writeString(file, GSON.toJson(root), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Yozora.LOGGER.warn("[Yozora] failed to save config", e);
        }
    }

    public void load(ru.yozora.modules.ModuleManager modules) {
        if (!Files.exists(file)) return;
        try {
            JsonObject root = GSON.fromJson(Files.readString(file, StandardCharsets.UTF_8), JsonObject.class);
            if (root == null) return;
            for (Module m : modules.all()) {
                if (!root.has(m.getName())) continue;
                JsonObject mo = root.getAsJsonObject(m.getName());
                if (mo == null) continue;
                if (mo.has("enabled")) m.setEnabled(mo.get("enabled").getAsBoolean());
                if (m instanceof HudModule hud && mo.has("x") && mo.has("y")) {
                    hud.x = mo.get("x").getAsInt();
                    hud.y = mo.get("y").getAsInt();
                }
            }
        } catch (IOException e) {
            Yozora.LOGGER.warn("[Yozora] failed to load config", e);
        }
    }
}
