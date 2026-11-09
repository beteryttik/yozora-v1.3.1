# Yozora Client (Fabric 1.21.11)

## Что внутри
- **Оптимизации:** ParticleCull, EntityCull, NoClouds, FpsLimiter
- **HUD:** FPS, XYZ, Ping, Keystrokes, Direction, Speed, Server, Time, Armor
- **Visual:** Zoom (C), FullBright, NoHurtCam, CustomCrosshair, SafeWalk
- **GUI:** Right Shift -> ClickGUI (категории, поиск, тумблеры)
- **Команды:** `/yozora` и `/yozora toggle <module>`
- **Конфиг:** `config/yozora.json` (автосохранение)

## Сборка
Gradle + Fabric Loom, JDK 21, mappings `1.21.11+build.2`.

## Правки v1.3.1 (по логу Actions)
- Убран `BlockEntityRenderDispatcherMixin` (класс отсутствует в маппингах 1.21.11)
- `ConfigManager`: `Gson.fromJson` вместо `Gson.parse`, `Files.readString` вместо `ReadString`
- HUD-модули: методы `HudRenderer.*()` без аргумента `client`
- `NoClouds`: `getCloudsValue()` вместо `getCloudsType()`
- `KeyInputHandler`: `KeyBinding.Category.create(Identifier.of(...))` вместо строки
- `HudManager`: явный импорт `ru.yozora.module.Module` (конфликт с `java.lang.Module`)
- `HudRenderer.armorText`: `getArmorItems()` вместо `getArmorStacks()`
- `ClickGuiScreen`: новый ввод мыши `mouseClicked(Click click, boolean doubled)` с `click.x()/y()`
- Поиск в GUI через `charTyped(CharInput)` — новый API 1.21.9+
