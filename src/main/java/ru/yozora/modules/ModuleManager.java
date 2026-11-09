package ru.yozora.modules;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> all = new ArrayList<>();

    public void add(Module m) {
        all.add(m);
    }

    public List<Module> all() {
        return all;
    }

    public List<Module> byCategory(Category c) {
        List<Module> out = new ArrayList<>();
        for (Module m : all) if (m.getCategory() == c) out.add(m);
        return out;
    }

    public Module byName(String name) {
        for (Module m : all) if (m.getName().equalsIgnoreCase(name)) return m;
        return null;
    }
}
