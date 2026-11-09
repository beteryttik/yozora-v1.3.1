package ru.yozora.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;
import ru.yozora.Yozora;
import ru.yozora.modules.Module;

public class YozoraCommand {
    public static void register() {
        LiteralArgumentBuilder arg = ClientCommandManager.literal("yozora")
                .executes(ctx -> {
                    StringBuilder sb = new StringBuilder("[Yozora] modules:\n");
                    for (Module m : Yozora.modules().all()) {
                        sb.append("- ").append(m.getName()).append(" [")
                                .append(m.isEnabled() ? "ON" : "OFF").append("]\n");
                    }
                    ctx.getSource().sendFeedback(Text.literal(sb.toString()));
                    return 1;
                });

        LiteralArgumentBuilder toggle = ClientCommandManager.literal("toggle")
                .then(ClientCommandManager.argument("module", StringArgumentType.word())
                        .executes(ctx -> {
                            String name = StringArgumentType.getString(ctx, "module");
                            Module m = Yozora.modules().byName(name);
                            if (m == null) {
                                ctx.getSource().sendError(Text.literal("[Yozora] unknown module: " + name));
                                return 0;
                            }
                            m.toggle();
                            Yozora.save();
                            ctx.getSource().sendFeedback(Text.literal(
                                    "[Yozora] " + m.getName() + " -> " + (m.isEnabled() ? "ON" : "OFF")));
                            return 1;
                        }));

        arg.then(toggle);

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(arg));
    }
}
